# Chagelog

## Unreleased
- should be 8:30-17:15?
- regular exression in `bump-version.sh` is loose
- usage sun.nio.fs.UnixPath
- FIXME: java.net/ConnectException at wdc.clj:34:14
- FIXME: taisya のログがない？


## 0.3.3 - 2023-03-10
- read .config/wdc/wdc.edn, not ~/.zshenv
- `case` does not take :else clause
- `require` even if the libraries are built in

## 0.3.2 - 2023-03-08
- added `bump-version.sh`

## 0.3.1 - 2023-03-07
- fixed: redirected log to log/wdc.log from automator scripts
- added: automator/ folder
- changed: convined two pmset scripts int pmset.sh. just two lines

## 0.3.0 - 2023-03-07
- API CHANGED: wdc.clj [in | out | version | log]
- directory call `wdc.clj` from Automator.
  instead, wdc env vars are defined in `.zshenv`.
- directory call `wdc.clj` from Automator
  instead, wdc env vars are defined in `.zshenv`
  new `wdc.sh` is simply invoking `wdc.clj`. not gitignored
- FIXME: log displays whole `wdc.log` file. It's tail is enough

## 0.2.4 - 2023-03-03
- timbre config adjustment
```
(timbre/merge-config!
 {:min-level :info
  :timestamp-opts
  {:pattern "yyyy-MM-dd HH:mm:ss"
   :timezone :jvm-default}})
```
## 0.2.3 - 2023-03-03
- `wdc --version` - show version info

## 0.2.2 - 2023-03-02
- `data/` - to keep pmset changes

## 0.2.1 - 2023-03-02
- `wdc.sh --log` - show log

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

## 0.1.4 - 2023-02-24
- pmset-sleep.sh
  changed sleep to sleep 60

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
- wake-before-wdc.sh: only one pmset repeat allowed
  use corn? not so good
- check out as m64:clojure/wdc
- symlink from m64:utils/wdc

## 0.1.0 - 2023-02-01
- created repository: github.com/hkim0331/wdc.git
