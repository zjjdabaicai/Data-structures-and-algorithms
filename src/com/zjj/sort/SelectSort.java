package com.zjj.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        selectSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }


    //选择排序
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            int min = arr[i];

            for (int j = i + 1; j < arr.length; j++) {

                //如果要降序  arr[minIndex] < arr[j]
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }

            if (minIndex != i) {    //优化~~~~~~
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第" + (i + 1) + "轮之后");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
