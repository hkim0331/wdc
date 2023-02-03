# Chagelog

- caution spelling, `syussya` and `taisya`.

## Unreleased
- command `at` is disabled on macos.
- caution spell `syussya` and `taisya`
- Automator does not work while mac is sleeping.
  Must wake mac up before Automator runs.
- 2023-02-03 error: java.net.ConnectException at wdc.clj:11:12
- log file's TIMEZONE is not JST


## 0.1.3 - 2023-02-03
- "env: bb:" No such file or directory
  #!/usr/bin/env bb is impossible in Automator?

## 0.1.2 - 2023-02-03
- #!/usr/bin/env bb
- exceptions when `babashka.http-client` errors
- logging in `log` folder
- `data` folder to keep error screen shots
- git ignored `log` and `data` folders

## 0.1.1 - 2023-02-02
- added wdc-example.sh
- use Automator
- wake-before-wdc.sh: only one pmset repeat allowed.
  use corn? not so good.
- check out as m64:clojure/wdc
- symlink from m64:utils/wdc


## 0.1.0 - 2023-02-01
- created repository: github.com/hkim0331/wdc.git
