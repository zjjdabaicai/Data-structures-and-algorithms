package com.zjj.Algorithm.binarysearchnorecursion;

/**
 * 二分查找非递归实现
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int arr[] = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        System.out.println("index=" + index);
    }


    /**
     * 二分查找非递归实现
     *
     * @param arr       待查找的数组，arr是升序
     * @param target    查找的目标数
     * @return          返回目标数对应下标，-1 表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
