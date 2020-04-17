package com.zjj.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        arrBinaryTree.infixOrder();
        arrBinaryTree.postOrder();
    }
}


/**
 * 数组
 */
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }


    //重载前序遍历
    public void preOrder() {
        this.preOrder(0);
    }
    //前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        System.out.println(arr[index]);

        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //重载中序遍历
    public void infixOrder() {
        this.infixOrder(0);
    }
    //中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }

        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }

        System.out.println(arr[index]);

        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }


    //重载后序遍历
    public void postOrder() {
        this.postOrder(0);
    }
    //后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组为空，不能遍历");
        }
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }


}