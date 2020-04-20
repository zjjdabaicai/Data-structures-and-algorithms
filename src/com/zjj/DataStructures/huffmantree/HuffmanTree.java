package com.zjj.DataStructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);

        //测试：
        preOrder(root);
    }


    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空的，你遍历什么！！！");
        }
    }

    //创建赫夫曼树的方法
    /**
     *
     * @param arr   需要创建成哈赫夫曼树的数组
     * @return      创建好后的赫夫曼树的root,根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {


            Collections.sort(nodes);
            System.out.println("nodes=" + nodes);


            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的节点
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的节点
            Node rightNode = nodes.get(1);
            //3.构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent加入到nodes
            nodes.add(parent);

//            System.out.println("第一次处理后" + nodes);
        }
        return nodes.get(0);
    }
}

//创建节点类
//为了让Node 对象持续排序Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node> {
    int value;  //节点权值
    Node left;  //指向左子节点
    Node right; //指向右子节点

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }



    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
