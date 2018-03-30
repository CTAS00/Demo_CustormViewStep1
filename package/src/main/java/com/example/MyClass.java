package com.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

// 打包替换名称
//http://xjk-attach.oss-cn-hangzhou.aliyuncs.com/download/app/xianjinka_166_7_NFD-YM-yima7_sign.apk
public class MyClass {
    public static String FILE_NAME = "/Users/koudai_nick/Desktop/名称替换文件夹";


    public static String ORIGIN_NAME = "xjk-attach.oss-cn-hangzhou.aliyuncs.com";
    public static String FUTURE_NAME = "res.xianjincard.com";


    public static String ORIGIN_FILE  = "打出的包修改.txt";
    public static String FUTURE_FILE  = "修改以后的文件夹.txt";

    public static void main(String args[]){
//        System.out.println("myclass");
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("文件不存在");
            return ;
        }

        File fa[] = file.listFiles();
        for(int i =0;i<fa.length;i++){
            File fs = fa[i];
            String name = fs.getName();
            System.out.println(name);
            if(name.endsWith(ORIGIN_FILE)){
                try {
                    FileInputStream inputStream = new FileInputStream(FILE_NAME + "/" +  name);
                    int len = 0;
                    byte[] buf = new byte[512];
                    StringBuilder sb = new StringBuilder();
                    while((len=inputStream.read(buf))!=-1){
                        sb.append(new String(buf,0,len));
                    }
                    System.out.println(sb);

                    // 获取到链接
                    String[] s  = sb.toString().split(".apk");
                    System.out.println("长度"+ s.length);
                    for(int j=0;j<s.length;j++){
                      String finaldata= doReplace(s[j]);
                        outputFile(finaldata);


                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    private static void outputFile(String data) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_NAME + "/" +  FUTURE_FILE),true));
            writer.write("\n"+data);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // 多种的替换方式
    private static String doReplace(String data) {
        // 截取一段
        String withoutsign = data.replace("_sign","");
        if(withoutsign.contains("xianjinka")){
            // 首次出现的位置
            int index = withoutsign.indexOf("_");
            int lastindex = withoutsign.lastIndexOf("_");
            String nodata = null;
            if(lastindex - index <= 7){
                // 容错处理
                nodata = withoutsign.substring(index+1,lastindex +1);
            }
           String widthouttag =  withoutsign.replace(nodata,"");
           String withoutapkdata =  widthouttag.replace(ORIGIN_NAME,FUTURE_NAME);
            String finaldata = withoutapkdata + ".apk";
            System.out.println(finaldata);
            return finaldata;
        }
        return null;


//        data.replace(ORIGIN_NAME,FUTURE_NAME);
    }
}
