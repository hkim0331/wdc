#!/bin/sh
#
# from pmset(1),
#
# Sets displaysleep to 10, disksleep to 10, system sleep to 30, and turns
# on WakeOnMagicPacket for ALL power sources (AC, Battery, and UPS) as
# appropriate
# pmset -a displaysleep 10 disksleep 10 sleep 30 womp 1

# old values are displaysleep 30 disksleep 10 sleep 1 womp1
# wakeorpoweron at 8:30,
# Automator exec wdc.sh in at 8:32,
# how about sleep = 10?
# 10 is now allowed
# Warning: Idle sleep timings for "AC Power" may not behave as expected.
# - Display sleep should have a lower timeout than system sleep.
# so, using 60.
pmset -a displaysleep 30 disksleep 20 sleep 60 womp 1
