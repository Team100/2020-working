#!/bin/bash
v4l2=$(command -v v4l2-ctl)
cid=$1

$v4l2 --device /dev/video$cid --set-ctrl=exposure_auto=1
$v4l2 --device /dev/video$cid --set-ctrl=exposure_absolute=5
