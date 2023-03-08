# wdc

WEB 打刻の忘却防止。

平日、9:00 に出勤、17:00 に退勤の打刻オペレーションを代行させる。
それより早い出勤、遅い退勤の手動オペレーションで上書きになる。

* Calendars に Automator 書類を仕込む。
* pmset で sleep のタイミングを Automator に合わせる。


# wdc/automator

Automator の書類は ~/Library/Workflows/Applications/ 以下にセーブされる。

~/Library/Workflows/Applications/Calendar/wdc-in.app/Contents/document.wflow
~/Library/Workflows/Applications/Calendar/wdc-out.app/Contents/document.wflow


## License

Free. 2023 Hiroshi Kimura



