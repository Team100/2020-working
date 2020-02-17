#!/bin/bash

set -e

source "$PWD/constants.sh"

./prepare_checkpoint_and_dataset.sh --network_type $NETWORK_TYPE

./retrain_detection_model.sh

./convert_checkpoint_to_edgetpu_tflite.sh

echo "DONE"
