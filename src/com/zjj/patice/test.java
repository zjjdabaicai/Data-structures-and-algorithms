package com.zjj.patice;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {

    }


    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid = 0;
        int k = 0;
        int[] f = fib();

        while (high > f[k]) {
            k++;
        }

        int[] temp = Arrays.copyOf(a, f[k]);
        for (int i = high + 1; i < a.length; i++) {
            temp[i] = a[high];
        }

        while (low < high) {
            mid = low + f[k - 1] - 1;
            if (key < mid) {
                high = mid - 1;
                k--;
            } else if (low > high) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid < high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;

    }


    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 1;
        f[2] = 1;
        for (int i = 0; i < f.length; i++) {
            f[i] = f[i - 1] + f[i + 2];

        }
        return f;
    }






}
