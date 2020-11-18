package Property;

public class Main {

  // PropertyUtil ( properties から設定を読込)
  static final PropertyUtil setting = new PropertyUtil("\\Property\\Setting");

  public static void main(String[] args) {

    try {
      System.out.println(System.getProperty("user.dir"));
      setting.readSetting(); // 設定読み込み
    } catch (Exception e) { // 例外処理
      System.out.println("（設定ファイルの読み込みに失敗しました。）");
      e.getMessage();
      System.exit(0); // 停止
    }

    // TeraTerm キャッシュ削除
    TeraTerm_deleteCustomerData();
    // Wireshark キャッシュ削除
    Wireshark_deleteCustomerData();

  }

  /*
   * TeraTerm キャッシュ削除処理
   */
  public static void TeraTerm_deleteCustomerData() {

    try {
      final LogFile TeraTerm = new LogFile(setting.getTeraTerm_path()); // TeraTerm
      System.out.printf("\n[ TeraTerm ] : %s\n", TeraTerm.getFile().getName());
      String contents = TeraTerm.readFile(); // ファイル内容の読み込み
      contents = TeraTerm.deleteCash(contents, setting.getTeraTerm_keyword()); // キャッシュ削除
      TeraTerm.writeFile(contents); // 削除後の内容を書き込み
      System.out.println("（キャッシュが削除されました。）");
      System.out.println("結果：成功\n");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("結果：失敗");
    }

  }

  /*
   * Wireshark キャッシュ削除処理
   */
  public static void Wireshark_deleteCustomerData() {

    try {
      final LogFile Wireshark = new LogFile(setting.getWireshark_path()); // Wireshark
      System.out.printf("\n[ Wireshark ] : %s\n", Wireshark.getFile().getName());
      String contents = Wireshark.readFile(); // ファイル内容の読み込み
      contents = Wireshark.deleteCash(contents, setting.getWireshark_keyword(), setting.getWireshark_keyword2()); // キャッシュ削除
      Wireshark.writeFile(contents); // 削除後の内容を書き込み
      System.out.println("（キャッシュが削除されました。）");
      System.out.println("結果：成功\n");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("結果：失敗");
    }

  }

}
