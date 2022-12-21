package com.ustc.webide.ideproject.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NPM {
    private String npm = "npm";
    private String run = "npm run dev";
    private String sd = "cd";
    void test(){
       // Process  p =new ProcessBuilder("ipconfig","/all").start();

    }

    public static void main(String[] args) {
        testProcessBuilder();
        //System.exit(1);
    }

public static String testProcessBuilder() {
    ProcessBuilder processBuilder = new ProcessBuilder();
//       processBuilder.command("ping","127.0.0.1");
    ///processBuilder.command("cd userFile");
    processBuilder.command("run.bat");
  //  processBuilder.command("run.bat");
    //将标准输入流和错误输入流合并，通过标准输入流读取信息

    processBuilder.redirectErrorStream(true);
    //System.exit(1);
    try {
        //启动进程
        Process start = processBuilder.start();
        //获取输入流
        InputStream inputStream = start.getInputStream();
        //转成字符输入流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        int len = -1;
        char[] c = new char[1024];
        StringBuffer outputString = new StringBuffer();
        //读取进程输入流中的内容
        while ((len = inputStreamReader.read(c)) != -1) {
            String s = new String(c, 0, len);
            outputString.append(s);
            //System.out.print(s);
            //Thread.sleep(10000);
        }
        inputStream.close();
        return outputString.toString();
    } catch (IOException e) {
        e.printStackTrace();
   } //catch (InterruptedException e) {
//        e.printStackTrace();
//    }
    return null;
    }
}
