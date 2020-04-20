package com.zjj.DataStructures.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//        int arr[] = {1, 2, 3, 10, 20};
//
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        //创建80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) Math.random() * 80000;
        }

        long l1 = System.currentTimeMillis();
        bubbleSort(arr);
        long l2 = System.currentTimeMillis();

        System.out.println(l2-l1);

//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));

    }


    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                //如果要降序则    arr[j] < arr[j + 1]
                if (arr[j] > arr[j + 1]) {
                    flag = true;

                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

            //优化~~~~~~~~~~~~~~
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
