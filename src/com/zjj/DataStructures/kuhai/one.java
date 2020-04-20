package com.zjj.DataStructures.kuhai;

import java.util.Scanner;

public class one {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int sum = test(i);
        System.out.println(sum);
    }

    public static int test(int n) {
        int sum = 0;
        int k = n;
        for (int i = 1; i <= n; i++, k--) {
            sum = sum + i * k;
        }
        return sum;
    }
}
