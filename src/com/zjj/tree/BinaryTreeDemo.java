package com.zjj.tree;

/**
 * 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //传建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建节点
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "无用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建该二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        binaryTree.setRoot(root);

        //测试遍历
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

        //测试查找
        /*System.out.println("前序查找");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }*/

        /*System.out.println("中序查找");
        HeroNode resNode = binaryTree.infixOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }*/

        /*System.out.println("后序查找");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }*/

        //测试删除
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();

    }
}


//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    /**
     * 遍历
     */
    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    /**
     * 查找
     * @param no
     * @return
     */
    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }


    /**
     * 删除
     * @param no
     */
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树不能删除");
        }
    }



    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}



//HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;


    /**
     * 遍历
     */
    //前序遍历方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //中序遍历方法
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);


    }

    /**
     * 查找
     */
    //前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找~~~");
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        return resNode;
    }
    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序查找~~~");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
            return resNode;
        }

        return resNode;
    }
    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序查找~~~");
        if (this.no == no) {
            return this;
        }

        return resNode;
    }


    /**
     * 删除
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }





    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
