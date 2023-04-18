#!/bin/sh
if [ "$1" = "" ]; then
  echo usage $0 version
  exit
fi
gsed -i.bak "/def version/c (def version \"$1\")" bb/wdc.clj

