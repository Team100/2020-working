#!/bin/bash

set -e

source "$PWD/constants.sh"

if [ -z ${NETWORK_TYPE+x} ]; then
  NETWORK_TYPE="mobilenet_v1_ssd"
fi

if [ -z ${TRAIN_WHOLE_MODEL+x} ]; then
  TRAIN_WHOLE_MODEL="false"
fi

if [ -z ${NUM_TRAINING_STEPS+x} ]; then
  NUM_TRAINING_STEPS=500
fi

if [ -z ${NUM_EVAL_STEPS+x} ]; then
  NUM_EVAL_STEPS=100
fi

echo $NETWORK_TYPE $TRAIN_WHOLE_MODEL $NUM_TRAINING_STEPS $NUM_EVAL_STEPS

./prepare_checkpoint_and_dataset.sh --network_type $NETWORK_TYPE --train_whole_model $TRAIN_WHOLE_MODEL

./retrain_detection_model.sh --num_training_steps $NUM_TRAINING_STEPS --num_eval_steps $NUM_EVAL_STEPS

./convert_checkpoint_to_edgetpu_tflite.sh --checkpoint_num $NUM_TRAINING_STEPS

echo "DONE"
