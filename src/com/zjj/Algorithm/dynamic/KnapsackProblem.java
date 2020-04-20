package com.zjj.Algorithm.dynamic;

/**
 * 动态规划：背包问题
 */

/**
 * (1)  v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0
 * (2) 当w[i]> j 时：v[i][j]=v[i-1][j]   // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
 * (3) 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  
 * // 当 准备加入的新增的商品的容量小于等于当前背包的容量,
 * // 装入的方式:
 * v[i-1][j]： 就是上一个单元格的装入的最大值
 * v[i] : 表示当前商品的价值
 * v[i-1][j-w[i]] ： 装入i-1商品，到剩余空间j-w[i]的最大值
 * 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]} :
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};    //物品的重量
        int[] val = {1500, 3000, 2000};     // 物品的价值
        int m = 4;  // 背包的容量
        int n = val.length;  // 物品的个数

        // 创建二维数组，表
        // v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，默认 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    // 将第一列设置为 0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;    // 将第一列设置为 0
        }


        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {

//                    v[i][j] = Math.max(v[i - 1][j], v[i] + v[i - 1][j - w[i]])
//                    v[i][j] = Math.max(v[i - 1][j], val[i-1] + v[i - 1][j - w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
