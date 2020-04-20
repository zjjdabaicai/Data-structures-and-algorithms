package com.zjj.DataStructures.kuhai;

import java.util.Arrays;

public class three {
    public static void main(String[] args) {

//        int gcd = gcd(319, 377);
//        System.out.println(gcd);

        String str1 = "11/8";
        int[] arr1 = string2Int(str1);
        System.out.println(Arrays.toString(arr1));
        int a1 = arr1[0];
        int b1 = arr1[1];

        String str2= "3/4";
        int[] arr2 = string2Int(str2);
        System.out.println(Arrays.toString(arr2));
        int a2 = arr2[0];
        int b2 = arr2[1];

        int a = a1 * b2 + a2 * b1;
        int b = b1 * b2;
        System.out.println("a=" + a + "  b=" + b);

        int gcd1 = gcd(a, b);

        a /= gcd1;
        b /= gcd1;
        System.out.println(a + "/" + b);
    }

    public static int[] string2Int(String str) {
        String[] s = str.split("/");

        int a  = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[1]);

        int[] num = new int[2];
        num[0] = a;
        num[1] = b;

        return num;
    }


    public static int gcd(int a, int b) {
        int temp = 0;
        while (a % b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }
}
