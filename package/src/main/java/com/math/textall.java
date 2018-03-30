package com.math;

/**
 * Created by koudai_nick on 2018/1/26.
 *o(n2)
 */

public class textall {

//    for(int j=i+1;j<s.length-1;j++){
//        int secondary = s[j];
//        if(first>secondary){
//            int temp = first;
//            first = secondary;
//            secondary = temp;
//        }
//
//    }

// 先找到最小的值,然后显示到第一项去。然后从第二项开始,再去找最小的值。

    private static void sortText(int arr[]){
            // 先找到最小的 并且放到第一项
        for(int j=0;j<arr.length-1;j++){
            for(int i=j;i<arr.length-1;i++){
                if(arr[j]>arr[i+1]){
                    int temp = arr[j];
                    arr[j] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
    }
//    int arr[] = {2,4,8,1,0};
    private static void bubb(int arr[]){
        // 总体遍历4次
        for(int i=0;i<arr.length-1;i++){
            // 第一次要遍历4次
            // 第二次要遍历3次
            for(int j=0;j<arr.length-1 - i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

            }
        }
    }
    public static void main(String args[]){
        int arr[] = {2,4,8,1,0};
//        int arr[] = {20,40,30,28,25};
        print(arr);
        System.out.println();
//        doSort(arr);
//        sortText(arr);
//        bubb(arr);
        dofinalquick(arr,0,arr.length-1);
//        int i =AdjustArray(arr,0,arr.length-1);
//        doquick(arr);
//        quick_sort1(arr,0,arr.length-1);
//        System.out.print("i="+i);
        System.out.println();
        print(arr);
    }
    //
    private static void doSort(int arr[]) {
        // 找到最小的
        for(int j=0;j<arr.length;j++){
            for(int i=j;i<arr.length - 1;i++){
                int min = arr[j];
                if(min > arr[i+1]){
                    int temp = arr[i+1];
                    arr[i+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void print(int[] s){
        for(int i=0;i<s.length;i++){
            System.out.print(s[i] + " ");
        }
    }

//    for(int i=0;i<arr.length-1;i++){
//        for(int j=0;j<arr.length-1-i;j++){
//            if(arr[j]>arr[j+1]){
//                int temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println();
//        print(arr);
//    }


    // 快速排序  先来6个数吧 {20,10,6,30,45,12}
    // {20,30,40,50,60,70}
    // 该方法能让第一个数据到其正确的位置上去                                                                                                                                                                                                                                                                                                                                                                                                                                                 找到第一个数的准确位置
    private static void doquick(int[] s){
        int x =s[0];
        int l = 0;
        int r = s.length -1;
        while(l<r){
            // 从右开始找比x小的数据
            while(l<r && s[r]>=x)
                r--;
            if(l<r){
                // 从第一项开始
                s[l] =s[r];
                l++;
            }
            while(l<r && s[l]<=x)
                l++;
            if(l<r){
                s[r] = s[l];
                r--;
            }
        }
        s[l] = x;
    }

    static void doall(int arr[],int l,int r){
        if(l<r){
            doquick(arr);
        }
    }

    // 为第一个数据找到合适地方去放
    static int  AdjustArray(int s[], int l, int r) //返回调整后基准数的位置
    {
        int i = l, j = r;
        int x = s[l]; //s[l]即s[i]就是第一个坑
        while (i < j)
        {
            // 从右向左找小于x的数来填s[i]
            while(i < j && s[j] >= x)
                j--;
            if(i < j)
            {
                s[i] = s[j]; //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(i < j && s[i] < x)
                i++;
            if(i < j)
            {
                s[j] = s[i]; //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;
        return i;
    }

    // {20,40,30,28,25};l=0 ,r=4
    static void quick_sort1(int s[], int l, int r)
    {
        if (l < r)
        {
            int i = AdjustArray(s, l, r);//先成挖坑填数法调整s[]
            quick_sort1(s, l, i - 1); // 递归调用
            quick_sort1(s, i + 1, r);
        }
    }




    static int quick(int[] arr,int left,int right){
        // 每次都是第一个
        int x = arr[left]; // 第一个坑已经挖出来了
        int l = left;
        int r = right;
        // 从右到左的去找
        // 一开始就找到坑的话 就可以直接填上去
        while(l<r){
            while(l<r && arr[r]>=x)
                r--;
            if(l<r){
                arr[l] = arr[r];
                l++;
            }
            while(l<r && arr[l]<=x)
                l++;
            if(l<r){
                arr[r] = arr[l];
                r--;
            }
        }
        arr[l] = x;
        return l;
    }

    static void dofinalquick(int[] arr,int l,int r){
        // 知道各个区间只有一个数的时候  就可以了
        if(l<r){
            int i=quick(arr,l,r);
            dofinalquick(arr,l,i-1);
            dofinalquick(arr,i+1,r);
        }
    }





    static void quick(int[] arr){
        int x = arr[0];
        int l =0;
        int r = arr.length - 1;
        // 从右往左边去找
        // 当l==r的时候 说明已经成功了
        while(l<r){
            while(l<r && arr[r]>x)
                r--;
            if(l<r){
                arr[l] = arr[r];
                l++;
            }
            while(l<r && arr[l]<x)
                l++;
            if(l<r){
                arr[r] =arr[l];
                r--;
            }
        }
    }



    static void onequick(int[] arr){
        int x = arr[0];
        int l = 0;
        int r = arr.length - 1;
        // 第一次循环结束
        while(l<r){
            // 从右边开始
            while(l<r&&x<arr[r])
                r--;
            if(l<r){
                arr[l]=arr[r];
                l++;
            }
            // 从左边开始
            while(l<r && x>arr[l])
                l++;
            if(l<r){
                arr[r] = arr[l];
                r--;
            }
        }
        arr[l] = x;
    }
    // 递归处理数据
    static void onesort(){

    }

}
