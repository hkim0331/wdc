#!/bin/sh

BB=/opt/homebrew/bin/bb

cd ${WDC_DIR}

WDC_URL="https://jinji-w.jimu.kyutech.ac.jp/cws/srwtimerec"
WDC_USER=""
WDC_PASS=""

${BB} -x wdc/in
# or
${BB} -x wdc/out




