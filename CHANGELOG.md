# Chagelog

- command `at` is disabled on macos.
- caution spell `syussya` and `taisya`

## Unreleased
- should be 8:30-17:15?

## 0.2.0 - 2023-03-01
- log in JST
  switched from `clojure.tools.logging` to `taoensso.timbre`
```
(timbre/merge-config!
 {:timestamp-opts
  {:pattern "yyyy-MM-dd HH:mm:ss"
   :timezone :jvm-default}})
```

## 0.1.5 - 2023-03-01
- copy wdc.sh to ~/bin/wdc
- added pmset-g.sh
- http-client 0.1.5

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
