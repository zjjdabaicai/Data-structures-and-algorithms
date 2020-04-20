package com.zjj.DataStructures.binarysorttree;

/**
 * 二叉排序树, 排序，增删
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        int[] arr = {10, 3};
        BinarySortTree binarySortTree = new BinarySortTree();

        //创建二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历
        System.out.println("中序遍历开始了");
        binarySortTree.infixOrder();

        //测试：删除叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(7);
        binarySortTree.delNode(10);

        System.out.println("root="+binarySortTree.getRoot());

        System.out.println("删除叶子节点");
        binarySortTree.infixOrder();
    }
}


//创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }


            Node parent = searchParent(value);

            if (targetNode.left == null && targetNode.right == null) {          //删除的节点是叶子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {   //删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            } else {    //删除只有一颗子树的节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }
        }
    }

    /**
     * 1.返回的以node为根节点的二叉排序树的最小节点的值
     * 2.删除node为根节点的二叉排序树的最小的值
     *
     * @param node  传入的节点（当做二叉排序树的根节点）
     * @return  返回的以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }



}



//创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {  //添加到左子树
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {                        //添加到右子树
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }



    /**
     * 查找要删除的节点
     *
     * @param value 希望删除节点的值
     * @return  如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (this.value == value) {              //找到并返回该节点
            return this;
        } else if (value < this.value) {        //如果查找的值小于当前节点，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {                                //如果查找的值大于当前节点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找到的节点的值
     * @return  返回的是要删除的节点的父节点，如果没有则返回null
     */
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {     //当前节点就是要删除节点的父节点，返回
            return this;
        }
        if (value < this.value && this.left != null) {          //要查找的值小于当前节点的值，并且当前节点的左子节点不为空
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null) { //要查找的值大于当前节点的值，并且当前节点的右子节点不为空
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }


}
