#!/bin/sh
# Admin user can set only one `repeat` job.
# to view repeat tasks, use `pmset -g sched`.

# wake at 8:30 weekdays and sleep at 19:30
pmset repeat wakeorpoweron MTWRF 8:30:00 sleep MTWRF 19:30:00


