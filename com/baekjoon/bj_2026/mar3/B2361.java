// https://www.acmicpc.net/problem/2631
package com.baekjoon.bj_2026.mar3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B2361 {
    static int N;
    static int[] arr;
    static int[] dp;
    static int answer;

    public static void main(String[] args) throws IOException {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 2; i <= N; i++) {
            for (int k = 0; k < i; k++) {
                if (arr[k] < arr[i]) {
                    dp[i] = Math.max(dp[k] + 1, dp[i]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(N - answer);
    }
}