package com.zjj.Algorithm.kmp;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2 = "BB";

        int[] next = kmpNext(str2);
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);

    }

    /**
     * 获取到一个字符串（子串）的部分匹配表
     *
     * @param dest 子串
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];    //KMP算法核心点
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }


    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，是子串对应的部分匹配表
     * @return 如果是 -1 就是没有匹配，否则返回第一个匹配的索引值
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {

            //KMP核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }

        return -1;



    }
}
