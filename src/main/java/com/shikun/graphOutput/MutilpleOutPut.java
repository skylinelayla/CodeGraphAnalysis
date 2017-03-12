package com.shikun.graphOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**控制两条输出流。为解析txt做准备
 * Created by shikun on 2017/3/12.
 */
public class MutilpleOutPut  {

    private static boolean fileLog = true;
    private static String logFileName = "temp.txt";//指定程序执行结果保存的文件路径

    public static OutputStream getOutputStream() throws IOException {

        if (fileLog) {
            File file = new File(logFileName);
            if (!file.exists())
                file.createNewFile();
            return new FileOutputStream(file, true);
        } else {
            return System.out;
        }
    }
    public static void log(String info) throws IOException{
        OutputStream out = getOutputStream();
        out.write(info.getBytes("utf-8"));
    }



}
