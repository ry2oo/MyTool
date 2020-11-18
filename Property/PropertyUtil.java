package Property;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyUtil {

  String settingFileName;
  private String TeraTerm_path, TeraTerm_keyword;
  private String Wireshark_path, Wireshark_keyword, Wireshark_keyword2;

  public PropertyUtil(String settingFileName) {
    setSettingFileName(settingFileName);
  }

  // 設定ファイルから値を取得
  public void readSetting() throws Exception {

    // getBundle の java.lang.NullPointerException は Main の設定ファイル名を間違えなければエラーが出ない想定
    // getString の NullPointerException はキーが間違えていなければエラーが出ない想定

    try {
      ResourceBundle rb = ResourceBundle.getBundle(settingFileName);
      this.TeraTerm_path = rb.getString("TeraTerm_FilePath");
      this.TeraTerm_keyword = rb.getString("TeraTerm_Keyword");
      this.Wireshark_path = rb.getString("Wireshark_FilePath");
      this.Wireshark_keyword = rb.getString("Wireshark_Keyword");
      this.Wireshark_keyword2 = rb.getString("Wireshark_Keyword2");
    } catch (MissingResourceException e) {
      System.out.println("（設定ファイルが見つかりません。）");
      throw e;
    } catch (Exception e) {
      throw e;
    }

  }

  public void setSettingFileName(String settingFileName) {
    this.settingFileName = settingFileName;
  }

  public String getTeraTerm_path() {
    return TeraTerm_path;
  }

  public String getTeraTerm_keyword() {
    return TeraTerm_keyword;
  }

  public String getWireshark_path() {
    return Wireshark_path;
  }

  public String getWireshark_keyword() {
    return Wireshark_keyword;
  }

  public String getWireshark_keyword2() {
    return Wireshark_keyword2;
  }

}