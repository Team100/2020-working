import collections
import cv2
import numpy as np
import tflite_runtime.interpreter as tflite
import platform

from messages_pb2 import BoundingBox as BoundingBoxMessage

EDGETPU_SHARED_LIB = {
	"Linux": "libedgetpu.so.1",
	"Darwin": "libedgetpu.1.dylib",
	"Windows": "edgetpu.dll"
}[platform.system()]

Object = collections.namedtuple('Object', ['id', 'score', 'bbox'])


class BoundingBox(object):
	def __init__(self, xmin, ymin, xmax, ymax, sx, sy):
		self.__bbox = BoundingBoxMessage(xmin=int(xmin*sx), ymin=int(ymin*sy), xmax=int(xmax*sx), ymax=int(ymax*sy))
	
	def __repr__(self):
		return f"<Bounding Box ({self.xmin}, {self.ymin}) ({self.xmax}, {self.ymax})>"
	
	@property
	def xmin(self):
		return self.__bbox.xmin
	
	@property
	def ymin(self):
		return self.__bbox.ymin

	@property
	def xmax(self):
		return self.__bbox.xmax
	
	@property
	def ymax(self):
		return self.__bbox.ymax
	
	@property
	def message(self):
		return self.__bbox

def input_size(interpreter):
	"""Returns input image size as (width, height) tuple."""
	_, height, width, _ = interpreter.get_input_details()[0]['shape']
	return width, height


def input_tensor(interpreter):
	"""Returns input tensor view as numpy array of shape (height, width, 3)."""
	tensor_index = interpreter.get_input_details()[0]['index']
	return interpreter.tensor(tensor_index)()[0]


def set_input(interpreter, image):
	"""Copies a resized and properly zero-padded image to the input tensor.

	Args:
		interpreter: Interpreter object.
		size: original image size as (width, height) tuple.
		resize: a function that takes a (width, height) tuple, and returns an RGB
			image resized to those dimensions.
	Returns:
		Actual resize ratio, which should be passed to `get_output` function.
	"""
	width, height = input_size(interpreter)
	h, w, _ = image.shape
	scale = min(width / w, height / h)
	w, h = int(w * scale), int(h * scale)
	resized = cv2.resize(image, (w, h))

	tensor = input_tensor(interpreter)
	tensor.fill(0)
	_, _, channel = tensor.shape
	tensor[:h, :w] = np.reshape(resized, (h, w, channel))
	return scale, scale


def output_tensor(interpreter, i):
	"""Returns output tensor view."""
	tensor = interpreter.tensor(interpreter.get_output_details()[i]['index'])()
	return np.squeeze(tensor)


def get_output(interpreter, score_threshold, image_scale=(1.0, 1.0)):
	"""Returns list of detected objects."""
	boxes = output_tensor(interpreter, 0)
	class_ids = output_tensor(interpreter, 1)
	scores = output_tensor(interpreter, 2)
	count = int(output_tensor(interpreter, 3))

	width, height = input_size(interpreter)
	image_scale_x, image_scale_y = image_scale
	sx, sy = width / image_scale_x, height / image_scale_y

	def make(i):
		ymin, xmin, ymax, xmax = boxes[i]
		return Object(
				id=int(class_ids[i]),
				score=float(scores[i]),
				bbox=BoundingBox(xmin, ymin, xmax, ymax, sx, sy))

	return [make(i) for i in range(count) if scores[i] >= score_threshold]

def load_labels(path, encoding="utf-8"):
	with open(path, 'r', encoding=encoding) as f:
		lines = f.readlines()
		if not lines:
			return {}

		if lines[0].split(' ', maxsplit=1)[0].isdigit():
			pairs = [line.split(' ', maxsplit=1) for line in lines]
			return {int(index): label.strip() for index, label in pairs}
		else:
			return {index: line.strip() for index, line in enumerate(lines)}

def make_interpreter(models, runner):
	return tflite.Interpreter(model_path=models[runner], experimental_delegates=[tflite.load_delegate(EDGETPU_SHARED_LIB, {})] if runner == "tpu" else [])

def calculate_angle(bounding_box, width, fov):
	cx = bounding_box.xmin + (bounding_box.xmax - bounding_box.xmin)/2
	return round((cx - (width/2) - 0.5) * (fov / width), 4)
