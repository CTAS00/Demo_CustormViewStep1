package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by koudai_nick on 2017/12/18.
 * 修改名称
 */

public class Rename {
    public static String FILE_NAME = "/Users/koudai_nick/Desktop/打包主要文件/名称替换_源";
    public static String FILE_END_NAME = "/Users/koudai_nick/Desktop/打包主要文件/名称替换_好";

    private static String CHANGE_FILE_NAME = "login_200_";
    private static String CHANGE_FILE_END_NAME ="_sign";

    private static final int FIRST_INDEX =9;
    private static final char LAST_INDEX = 'N';
    public static void main(String args[]){
            File file = new File(FILE_NAME);
        File[] filelist = file.listFiles();
//             System.out.println(files[0]);
        int i=1;
        for (File file1 : filelist) {
            String filename = file1.getName();
            if(filename.contains(".DS_Store")){
                continue;
            }
            String finalfilename = filename.replace(CHANGE_FILE_END_NAME,"");
            System.out.println(CHANGE_FILE_NAME + i + "_");
            // 找到c的位置
            int charIndex = 0;
            char[] chars = finalfilename.toCharArray();
            for(int j=0;j<chars.length;j++){
                if(LAST_INDEX == chars[j]){
                    charIndex = j;
                    break;
                }
            }
            System.out.println("当前字母的位置 = " + charIndex);
            String changename = finalfilename.substring(FIRST_INDEX + 1,charIndex);
            String finalname = finalfilename.replace(changename,"");
            System.out.println(finalname);
            file1.renameTo(new File(FILE_END_NAME + "/"+ finalname));
        }

//        File file = new File(FILE_NAME);
//
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            FileOutputStream fos = new FileOutputStream("/Users/koudai_nick/Desktop");
//            byte[] buf = new byte[512];
//            int len = 0;
////            StringBuilder sb = new StringBuilder();
//            while((len =fis.read(buf))!=-1){
////                sb.append(buf,0,len);
//                fos.write(buf,0,len);
//            }
//
//
//            fos.flush();
//            fos.close();
//            fis.close();
//
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
