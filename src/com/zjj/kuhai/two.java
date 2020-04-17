package com.zjj.kuhai;

import java.util.Scanner;

/**
 * 将数字反转，   再求和
 */
public class two {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int sum = sum(a, b);
        System.out.println(sum);
    }

    public static int sum(int a, int b) {
        return (reverse(a) + reverse(b));
    }

    public static int reverse(int num) {


        int sum = 0;
        int a = num;
        int b = 0;

        while (a != 0) {
            b = a % 10;
            a = a / 10;

            sum = sum * 10 + b;
        }
        return sum;

    }
}
