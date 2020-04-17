package com.zjj.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random()*10000);
        }
//        int[] arr = {-1, 78, 0, 23, -567, 70, -2};

        quickSort1(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }


    //我能理解的快速排序
    public static void quickSort1(int[] arr, int left, int right) {

        if (left > right) {
            return;
        }

        int temp = 0;
        int base = arr[left];
        int l = left;
        int r = right;

        while (l != r) {
            //注意：要先从右边开始
            //要降序则修改这里的符号   arr[r] <= base && l < r
            while (arr[r] >= base && l < r) {
                r--;
            }
            //要降序则修改这里的符号   arr[l] >= base && l < r
            while (arr[l] <= base && l < r) {
                l++;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }

        arr[left] = arr[l];
        arr[l] = base;

        quickSort1(arr, left, l - 1);
        quickSort1(arr, r + 1, right);
    }


    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;

        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        if (l == r) {
            l += 1;
            r -= 1;
        }

        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
