#!/bin/sh
# need sudo to execute
#
# to check the settings, use
# % pmset -g
# % pmset -g sched

pmset -a displaysleep 30 disksleep 30 sleep 60 womp 1
pmset repeat wakeorpoweron MTWRF 8:25:00 sleep MTWRF 19:30:00
