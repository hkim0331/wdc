#!/bin/zsh
export WDC_DIR=$HOME/utils/wdc
/opt/homebrew/bin/bb ${WDC_DIR}/bb/wdc.clj out >> ${WDC_DIR}/log/wdc.log
