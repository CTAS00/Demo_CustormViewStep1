package com.datastructure;

import javax.swing.text.View;

/**
 * Created by koudai_nick on 2018/3/12.
 */

public class Text {
    public static void main(String argsp[]){
        BinaryTree binaryTree = new BinaryTree();
        TreeNode node =  binaryTree.init();
        // 先序遍历
//        binaryTree.theFirstTrav(node);
//        System.out.println();

//        doMath();
//        // 中序遍历
//        binaryTree.theCenterTrav(node);
//        System.out.print("当前的数据 =" + aadd(100));


        // 测试
        initMath();
    }

    private static void initMath() {
        String result = "authentication?mid=N";
        int index =result.indexOf("?");
        String finalResult = result.substring(index+1);
        int equalindex = finalResult.indexOf("=");
        String key = finalResult.substring(0,equalindex);
        String value = finalResult.substring(equalindex+1);
    }

    public static int aadd(int n){
        int sum;
        if(n==1){
            return 1;
        }
        sum= n +aadd(n-1);
        return sum;
    }
    // 相加到100
    public static int sum100(int n){
        int sum;
        // 终止的条件
        if(n ==100){
            return 100;
        }
        sum = n+sum100(n+1);
        return sum;
    }


//    public static View find(ViewGroup vg,int id){
//        if(vg ==null){
//            return null;
//        }
//        int size = vg.getChildCount();
//        for(int i=0;i<size;i++){
//            View view = vg.getChildAt(i);
//            if(view.getId() == id){
//                return view;
//            }
//            if(view instance of ViewGroup){
//                View view = find((ViewGroup)view,id);
//                if(view!=null){
//                    return view;
//                }
//            }
//
//        }
//        return null;
//    }


    public static void doMath(){
        String text = "ss";
        int[] list = new int[]{1,2,3,4,5,6};
//        System.out.println("当前的数量 ==" + list.length);
        for(;;){
            if(text!=null){
                return;
            }
            System.out.println("当前的i =="+list.length);
        }
//        System.out.println("走下去了！！");
    }
}
