package com.example;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import sun.rmi.runtime.Log;

/**
 * Created by koudai_nick on 2017/12/14.
 */

public class Text {

    private static final String NAME = "SEM-BD-tg";
    private static final String FIRST_NAME = "UMENG_CHANNEL";

    public static String FILE_NAME = "/Users/koudai_nick/Desktop/打包主要文件/oss.txt";

    public static void main(String args[]){

        File file = new File(FILE_NAME);
//        try {
//
//          DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));
//            StringBuilder sb = new StringBuilder();
////            SEM-BD-tg00001到SEM-BD-tg00100
//            NumberFormat f=new DecimalFormat("00000");
//            for(int i=1;i<=500;i++){
//                System.out.println(NAME + f.format(i));
//                String finalName = NAME + f.format(i);
//                sb.append(FIRST_NAME + " " + finalName + " " +  finalName + "\n");
//            }
////            fos.writeUTF(sb.toString());
//           fos.writeChars(sb.toString());
//            fos.flush();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            PrintWriter pfp = new PrintWriter(file, "UTF-8"); //设置输出文件的编码为utf-8
            StringBuilder sb = new StringBuilder();
//            SEM-BD-tg00001到SEM-BD-tg00100
            NumberFormat f=new DecimalFormat("00000");
            for(int i=1;i<=500;i++){
                System.out.println(NAME + f.format(i));
                String finalName = NAME + f.format(i);
                sb.append(FIRST_NAME + " " + finalName + " " +  finalName + "\n");
            }
//            fos.writeUTF(sb.toString());
            pfp.print(sb.toString());
            pfp.flush();
            pfp.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
