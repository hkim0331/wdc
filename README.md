# Web Dakoku Correct

WEB 打刻の忘却防止。

平日、9:00 に出勤、17:00 に退勤の打刻オペレーションを代行させる。
レコードはそれより早い出勤、遅い退勤の手動オペレーションで上書きになる。

## Usage
1. ~/.config/wdc/wdc.edn を作成する
```
{:wdc-url  "https://jinji-w.jimu.kyutech.ac.jp/cws/srwtimerec"
 :wdc-log  "~/utils/wdc/log/wdc.log"
 :wdc-user "********"
 :wdc-pass "########"
 }
  ```
2. (optional) インタラクティブに wdc.clj を呼び出す wdc.sh を ~/bin にインストールする。
```
%  bb install
```
3. Automator で定時に wdc.clj in あるいは wdc.clj out を呼び出すよう仕掛ける。
4. (Don't forget) Automator はスリープ中には起動しない。
```
% sudo pmset.sh
```

## License

Free.

2023 Hiroshi Kimura
