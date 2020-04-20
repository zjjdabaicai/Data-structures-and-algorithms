package com.zjj.DataStructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {

        //测试压缩文件
//        String srcFile = "d://src.bmp";
//        String dstFile = "d://dst.zip";
//
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件ok~~~~~");

        //测试解压文件
        String srcFile = "d://dst.zip";
        String dstFile = "d://src2.bmp";

        unZipFile(srcFile, dstFile);
        System.out.println("解压OK~~~~");

/*        String str = "i like like like java do you like a java";
        byte[] contenBytes = str.getBytes();
        System.out.println(contenBytes.length); //40

        byte[] huffmanCodesBytes = huffmanZip(contenBytes);
        System.out.println("压缩后的结果：" + Arrays.toString(huffmanCodesBytes) + " 长度是：" + huffmanCodesBytes.length);

        //测试 byteToBitString：
//        System.out.println(byteToBitString((byte) -1));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));*/




       /* List<Node> nodes = getNodes(contenBytes);
        System.out.println("nodes= " + nodes);  //nodes= [Node{data=32, weight=9}, Node{data=97, weight=5}, Node{data=100, weight=1}, Node{data=101, weight=4}, Node{data=117, weight=1}, Node{data=118, weight=2}, Node{data=105, weight=5}, Node{data=121, weight=1}, Node{data=106, weight=2}, Node{data=107, weight=4}, Node{data=108, weight=4}, Node{data=111, weight=2}]

        //测试，创建的赫夫曼树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //测试：是否生成了对应的赫夫曼编码
//        getCodes(huffmanTreeRoot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表" + HuffmanCode.huffmanCodes);

        //测试:将赫夫曼编码进行压缩
        byte[] huffmanCodeBytes = zip(contenBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));    //huffmanCodeBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
*/

    }


    /**
     * 解压文件
     *
     * @param zipFile   解压的文件
     * @param dstFile   将文件解压到哪个位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 将一个文件进行压缩
     *
     * @param srcFile   传入压缩文件的全路径
     * @param dstFile   传到的目标路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //注意：一定要把赫夫曼编码写入压缩文件！！！！！
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //完成数据的解压
    //1.将huffmanCodeByte[] 数组转成 赫夫曼编码对应的二进制的字符串
    //2.赫夫曼编码对应的二进制的字符串=》对照赫夫曼编码 转换成 字符

    /**
     * 完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());

        //2.把字符串转换成指定的赫夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map=" + map);

        //3.创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        //4.将list中的数据放入byte并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }

        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不补
     * @param b    传入的byte
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);  //返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 功能：将方法封装起来，方便调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的自己的数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码（根据 赫夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }


    //将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder=" + stringBuilder.toString());
//        System.out.println(stringBuilder.length());

        //将“1010100010111......”转成byte[]

        //统计返回 byte[] buffmanCodeBytes长度
        //简化：int len = (stringBuilder.length()+7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的bytes数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;  // 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {   //防止最后一个字节不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转成一个byte，放入到huffmanCodeBytes数组
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //1.将赫夫曼编码表存放在Map<Byte,String>形式 32->01 97->100 100->110000 ,不一定是这种
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径： 1：左子节点  2：右子节点
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);

        if (node != null) {
            //判断当前node 是叶子节点还是非叶子节点
            if (node.data == null) {    //非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {    //叶子节点
                //就表示找到某个叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }


    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * @param bytes 接受字节数组
     * @return 返回List形式   [Node[date=97 ,weight = 5], Node[date=32,weight = 9]......]
     */
    private static List<Node> getNodes(byte[] bytes) {

        //1.创建一个ArrayList
        List<Node> nodes = new ArrayList<>();

        //2.遍历bytes，统计每一个byte出现的次数 ->map[key,value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, counts.get(b) + 1);
            }
        }

        //3.将每个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }


    //通过List创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二课最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，注意：它的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //nodes 最后的节点，就是赫夫曼树的根节点
        return nodes.get(0);
    }
}


//创建Node，数据和权值
class Node implements Comparable<Node> {
    Byte data;  //存放数据（字符）本身，比如'a' => 97     ' ' => 32
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


}
