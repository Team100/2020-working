import hashlib
import json
import logging
import os
import random

import contextlib2
import tensorflow as tf

from object_detection.dataset_tools import tf_record_creation_util
from object_detection.utils import dataset_util

flags = tf.app.flags
flags.DEFINE_string("data_dir", "./image_data", "Path to directory with annotated data")
flags.DEFINE_string("output_dir", ".", "Path to directory to output TFRecords")
flags.DEFINE_integer("num_shards", 10, "Number of TFRecord shards")
FLAGS = flags.FLAGS

def dict_to_tf_example(annotation_path, image_path, file_type):
  annotation = json.load(open(annotation_path))

  height = annotation["size"]["height"]
  width = annotation["size"]["width"]

  with tf.gfile.GFile(image_path, "rb") as file_descriptor:
    encoded_image = file_descriptor.read()
  key = hashlib.sha256(encoded_image).hexdigest()

  xmins = []
  xmaxes = []
  ymins = []
  ymaxes = []
  classes_text = []
  classes = []
  
  objects = annotation["objects"]
  for obj in objects:
    points = obj["points"]["exterior"]
    if len(points) != 0:
      xmins.append(points[0][0] / width)
      ymins.append(points[0][1] / height)
      xmaxes.append(points[1][0] / width)
      ymaxes.append(points[1][1] / height)
      classes_text.append(obj["classTitle"].encode())
      classes.append(1)

  return tf.train.Example(features=tf.train.Features(feature={
    "image/height": dataset_util.int64_feature(height),
    "image/width": dataset_util.int64_feature(width),
    "image/filename": dataset_util.bytes_feature(image_path.encode()),
    "image/source_id": dataset_util.bytes_feature(image_path.encode()),
    "image/key/sha256": dataset_util.bytes_feature(key.encode()),
    "image/encoded": dataset_util.bytes_feature(encoded_image),
    "image/format": dataset_util.bytes_feature(file_type.encode()),
    "image/object/bbox/xmin": dataset_util.float_list_feature(xmins),
    "image/object/bbox/xmax": dataset_util.float_list_feature(xmaxes),
    "image/object/bbox/ymin": dataset_util.float_list_feature(ymins),
    "image/object/bbox/ymax": dataset_util.float_list_feature(ymaxes),
    "image/object/class/text": dataset_util.bytes_list_feature(classes_text),
    "image/object/class/label": dataset_util.int64_list_feature(classes)
  }))

def create_tf_record(output_filename, num_shards, images):
  with contextlib2.ExitStack() as tf_record_close_stack:
    output_tfrecords = tf_record_creation_util.open_sharded_output_tfrecords(
        tf_record_close_stack, output_filename, num_shards)

    for index, image in enumerate(images):
      annotation = image.replace("img", "ann") + ".json"
      tf_example = dict_to_tf_example(annotation, image, os.path.splitext(image)[1])
      if tf_example:
        shard_idx = index % num_shards
        output_tfrecords[shard_idx].write(tf_example.SerializeToString())

def main(_):
  data_dir = FLAGS.data_dir

  logging.info('Reading from dataset.')
  fd1i_image = [data_dir + "/fd1i/img/" + f for f in os.listdir(data_dir + "/fd1i/img/") if f.endswith(".jpg")]
  fd1v_image = [data_dir + "/fd1v/img/" + f for f in os.listdir(data_dir + "/fd1v/img/") if f.endswith(".png")]
  fd2v_image = [data_dir + "/fd2v/img/" + f for f in os.listdir(data_dir + "/fd2v/img/") if f.endswith(".png")]
  images = fd1i_image + fd1v_image + fd2v_image

  # Test images are not included in the downloaded data set, so we shall perform
  # our own split.
  random.shuffle(images)
  num_images = len(images)
  num_train = int(0.7 * num_images)
  train_images = images[:num_train]
  val_images = images[num_train:]
  logging.info('%d training and %d validation examples.',
               len(train_images), len(val_images))

  train_output_path = os.path.join(FLAGS.output_dir, 'train.record')
  val_output_path = os.path.join(FLAGS.output_dir, 'val.record')

  create_tf_record(
      train_output_path,
      FLAGS.num_shards,
      train_images)
  create_tf_record(
      val_output_path,
      FLAGS.num_shards,
      val_images)

if __name__ == "__main__":
    tf.app.run()
