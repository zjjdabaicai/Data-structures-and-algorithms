package com.zjj.DataStructures.tree;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9, -2, 99, 898};
        heapSort(arr);

    }


    //堆排序的方法        升序，大顶堆      降序，小顶堆
    public static void heapSort(int arr[]) {

        System.out.println("堆排序");

        //分布完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));

        //将无序序列构建成一个堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }



        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));

    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆     从树下面开始调整
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length 是在主键的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

}
