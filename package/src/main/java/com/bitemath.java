package com;

/**
 * Created by koudai_nick on 2018/1/29.
 */

public class bitemath {

    public static void main(String args[]){
//      二进制0000 1111    >>往右移动两位 0000 0011  一个字节
        int b = 15;
        // 向右移动两位
        System.out.print("数据:" + (b >> 2));
        System.out.println();
        System.out.print("数据:" + isEven(b));
        swap(20,30);

    }


    // 判断奇偶的二进制数据  Even 偶数  unEven 奇数
    // 两个位都为1的时候,结果才为1  &  与运算
    // 两个都为0的时候,结果才为0    |  或运算
    // Even偶数   unEven奇数
    // 是不是偶数 isEven 是不是偶数  两个位都为1时,结果才为1
    static String isEven(int data){
        if((data&1) == 1){
            return "奇数";
        } else {
            return "偶数";
        }
    }
    // 两个位相同为0 相异为1  异或的意思
    static void swap(int a,int b){
        if(a!=b){
            a^=b;
            b^=a;
            a^=b;
        }
        System.out.print("数据a:" + String.valueOf(a) + "数据b:"  + String.valueOf(b));
    }
}
