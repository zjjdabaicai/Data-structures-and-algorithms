package com.zjj.Algorithm.dac;

/**
 * 汉诺塔，分治算法
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(2, 'A', 'B', 'C');
        System.out.println(count);
    }

    static int count = 0;


    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
            count++;
        } else {
            //1.先把最上面所有的盘A->B，移动过程会用到C
            hanoiTower(num - 1, a, c, b);
            // 2. 把最下面的盘A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            ++count;
            // 3. 把B塔的所有盘从B->C，移动过程使用到 a 塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}

