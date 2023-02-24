# Chagelog

- command `at` is disabled on macos.
- caution spell `syussya` and `taisya`

## Unreleased
- log file's TIMEZONE is not JST
- Automator does not work while mac is sleeping.
  Must wake mac up before Automator runs.
- pmset wake してもネットが不通な感じ。WiFi生きてたらつながるのか？
  スリープ中に外から ping か ssh で起こすのは？それとも別PCで？
  => WiFi 入れてたら動くのか？

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
