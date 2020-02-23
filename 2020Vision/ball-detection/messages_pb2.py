# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: robot-server/messages.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='robot-server/messages.proto',
  package='',
  syntax='proto3',
  serialized_options=None,
  serialized_pb=b'\n\x1brobot-server/messages.proto\">\n\x08HighGoal\x12\x10\n\x08\x64istance\x18\x01 \x01(\x01\x12\x0f\n\x07v_angle\x18\x02 \x01(\x01\x12\x0f\n\x07h_angle\x18\x03 \x01(\x01\"H\n\nPowerCells\x12\x1b\n\x05\x62oxes\x18\x01 \x03(\x0b\x32\x0c.BoundingBox\x12\x0e\n\x06\x61ngles\x18\x02 \x03(\x01\x12\r\n\x05\x66ound\x18\x03 \x01(\x08\"E\n\x0b\x42oundingBox\x12\x0c\n\x04xmin\x18\x01 \x01(\x05\x12\x0c\n\x04xmax\x18\x02 \x01(\x05\x12\x0c\n\x04ymin\x18\x03 \x01(\x05\x12\x0c\n\x04ymax\x18\x04 \x01(\x05\x62\x06proto3'
)




_HIGHGOAL = _descriptor.Descriptor(
  name='HighGoal',
  full_name='HighGoal',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='distance', full_name='HighGoal.distance', index=0,
      number=1, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='v_angle', full_name='HighGoal.v_angle', index=1,
      number=2, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='h_angle', full_name='HighGoal.h_angle', index=2,
      number=3, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=31,
  serialized_end=93,
)


_POWERCELLS = _descriptor.Descriptor(
  name='PowerCells',
  full_name='PowerCells',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='boxes', full_name='PowerCells.boxes', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='angles', full_name='PowerCells.angles', index=1,
      number=2, type=1, cpp_type=5, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='found', full_name='PowerCells.found', index=2,
      number=3, type=8, cpp_type=7, label=1,
      has_default_value=False, default_value=False,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=95,
  serialized_end=167,
)


_BOUNDINGBOX = _descriptor.Descriptor(
  name='BoundingBox',
  full_name='BoundingBox',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='xmin', full_name='BoundingBox.xmin', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='xmax', full_name='BoundingBox.xmax', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='ymin', full_name='BoundingBox.ymin', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='ymax', full_name='BoundingBox.ymax', index=3,
      number=4, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=169,
  serialized_end=238,
)

_POWERCELLS.fields_by_name['boxes'].message_type = _BOUNDINGBOX
DESCRIPTOR.message_types_by_name['HighGoal'] = _HIGHGOAL
DESCRIPTOR.message_types_by_name['PowerCells'] = _POWERCELLS
DESCRIPTOR.message_types_by_name['BoundingBox'] = _BOUNDINGBOX
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

HighGoal = _reflection.GeneratedProtocolMessageType('HighGoal', (_message.Message,), {
  'DESCRIPTOR' : _HIGHGOAL,
  '__module__' : 'robot_server.messages_pb2'
  # @@protoc_insertion_point(class_scope:HighGoal)
  })
_sym_db.RegisterMessage(HighGoal)

PowerCells = _reflection.GeneratedProtocolMessageType('PowerCells', (_message.Message,), {
  'DESCRIPTOR' : _POWERCELLS,
  '__module__' : 'robot_server.messages_pb2'
  # @@protoc_insertion_point(class_scope:PowerCells)
  })
_sym_db.RegisterMessage(PowerCells)

BoundingBox = _reflection.GeneratedProtocolMessageType('BoundingBox', (_message.Message,), {
  'DESCRIPTOR' : _BOUNDINGBOX,
  '__module__' : 'robot_server.messages_pb2'
  # @@protoc_insertion_point(class_scope:BoundingBox)
  })
_sym_db.RegisterMessage(BoundingBox)


# @@protoc_insertion_point(module_scope)