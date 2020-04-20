package com.zjj.DataStructures.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1, -1, 89};

        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        insertSort(arr);

        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }


    //插入排序
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i];
            int insertIndex = i - 1;

            //如果要降序，则  insertValue >= arr[insertIndex]
            while (insertIndex >= 0 && insertValue <= arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex + 1 != i) {     //优化~~~~~~~~~~~~~
                arr[insertIndex + 1] = insertValue;
            }

        }
    }
}
