package com.zjj.DataStructures.recursion;

/**
 * 八皇后，回溯算法
 */
public class Queue8 {

    //定义一个max表示有多少行
    int max = 8;

    int[] array = new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue = new Queue8();
        queue.check(0);
        System.out.printf("有解法：%d 种", count);
        System.out.printf("冲突次数：%d 种", judgeCount);

    }


    //放置第n个皇后
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否有冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    //
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后摆放的位置打印出来
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
