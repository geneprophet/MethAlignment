package ngdc.cn;
/**
 * 读取filePath的文件，将文件中的数据按照行读取到String数组中，每行格式：甲基化位点    L/M/H
 * @author Kang
 * @param filePath	文件的路径
 * @return			文件中一行一行的数据
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {
    String filePath;
    public ReadFile(String filePath) {
        this.filePath = filePath;
    }
    public String ReadFileToString(){

        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {


            Long filelength = file.length(); // 获取文件长度

            byte[] filecontent = new byte[filelength.intValue()];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
          return new String(filecontent);
    }else return "ERROR:no file or file is empty!";
    }


}

