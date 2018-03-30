package com.datastructure;


/**
 * Created by koudai_nick on 2018/3/12.
 *       A
 *     B   C
 *    D   E  F
 */

public class BinaryTree {
    public TreeNode init(){
        TreeNode D = new TreeNode(null,null,"D");
        TreeNode E = new TreeNode(null,null,"E");
        TreeNode F = new TreeNode(null,null,"F");
        TreeNode B = new TreeNode(D,E,"B");
        TreeNode C = new TreeNode(F,null,"C");
        return new TreeNode(B,C,"A");
    }
    public void printNode(TreeNode treeNode){
        System.out.println(treeNode.getValue());
    }
    // 先序遍历  每一个结点都是相同的处理方案
    public void theFirstTrav(TreeNode root){
        if(root!=null){
            System.out.println("进来");
            printNode(root);
            // 递归左边 不断去找到最左边的数据
            if(root.getLeft()!=null){
                System.out.println("left进来");
                theFirstTrav(root.getLeft());
            }
            // 递归右边
            if(root.getRight()!=null){
                System.out.println("right进来");
                theFirstTrav(root.getRight());
            }
        }

    }
    // 中序遍历
    public void theCenterTrav(TreeNode root){
        // 当左右子树都为空的情况下  就不会遍历了
        if(root.getLeft()!=null){
            theCenterTrav(root.getLeft());
        }
        printNode(root);
        if(root.getRight()!=null){
            theCenterTrav(root.getRight());
        }
    }
    // 先序遍历  用的是分治法的思想 把事情给细分成小问题来解决。
    public void theFirstTwoTrav(TreeNode root){
        // 访问的是根结点 然后再左再右
        printNode(root);
        // 遍历左边的子树
        if(root.getLeft()!=null){
            // 一直能把左边的给遍历到底
            theFirstTrav(root.getLeft());
        }
        if(root.getRight()!=null){
            theFirstTrav(root.getRight());
        }
    }
    // 再次理解 先序
    // 中序打印的要求就是先将左边的都处理玩 然后中间的  最后是右边的
    public void theFirstThreeTrav(TreeNode root){
        // 每一个进来都会做相同的事情
        printNode(root);
        if(root.getLeft()!=null){
            theFirstThreeTrav(root.getLeft());
        }
        if(root.getRight()!=null){
            theFirstThreeTrav(root.getRight());
        }
    }






    public void theFirstFourTrav(TreeNode root){
        // 先序遍历的话就是 就是NLR的处理就可以了


    }



}
