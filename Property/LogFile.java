package Property;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LogFile {

  private File file;

  // コンストラクタ
  public LogFile(String path) throws Exception {
    file = new File(path);
    checkFile();
  }

  /*
   * ファイルチェック
   */
  public void checkFile() throws Exception {
    if (!file.exists()) { // ファイル存在
      throw new Exception("（ファイルが存在しません。）");
    } else if (!file.canRead()) { // 読み込み権限
      throw new Exception("（読み込み権限がありません。）");
    } else if (!file.canWrite()) { // 書き込み権限
      throw new Exception("（書き込み権限がありません。）");
    }
  }

  /*
   * 読み込み
   */
  public String readFile() throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();

    String str = br.readLine(); // ファイルから1行分の文字列取得
    while (str != null) { // 行がなくなるまで繰り返し
      sb.append(str).append("\n"); // sb に str を連結し改行
      str = br.readLine(); // 次の行に移動
    }
    br.close(); // br close
    String contents = sb.toString(); // sb を String 型へ

    return contents.toString(); // 取得したファイル内容を戻す
  }

  /*
   * 書き込み
   */
  public void writeFile(String contents) throws Exception {

    String str = readFile(); // 削除前のデータ
    if (str.equals(contents)) { // 削除前と比較して差分が無い場合
      throw new Exception("（指定した範囲にキャッシュが存在しません。）");
    }

    FileWriter fw = new FileWriter(file);
    PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

    // 削除できるキャッシュがあった場合
    pw.print(contents); // 削除後のデータを書き込み
    pw.close(); // pw close
  }

  /*
   * キャッシュ削除 ( keyword 以降を削除)
   */
  public String deleteCash(String contents, String keyword) throws Exception {

    int result = contents.indexOf(keyword); // keyword が最初に出現する位置
    if (result == -1) { // keyword 見つからなかった場合
      throw new Exception(keyword + "（該当する文字列が見つかりません。）");
    }

    String str = contents.substring(0, result + keyword.length()) + "\n"; // keyword 以降を削除

    return str; // キャッシュ削除後の内容を戻す
  }

  /*
   * キャッシュ削除 ( keyword から keyword2 までを削除)
   */
  public String deleteCash(String contents, String keyword, String keyword2) throws Exception {

    String str = deleteCash(contents, keyword); // keyword より前の文字列

    int result = contents.lastIndexOf(keyword2); // keyword2 が最後に出現する位置
    if (result == -1) { // keyword2 見つからなかった場合
      throw new Exception(keyword2 + "（該当する文字列が見つかりません。）");
    }

    str += contents.substring(result); // keyword2 以降の文字列を連結

    return str; // キャッシュ削除後の内容を戻す
  }

  public File getFile() {
    return file;
  }

}