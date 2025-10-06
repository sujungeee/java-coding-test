// https://www.acmicpc.net/problem/2302
package com.baekjoon.oct_2025;

/**
 * 2302: 극장 좌석
 * # summary
 * : 사람들이 좌석을 앉을 수 있는 경우의 수
 * # access
 * 1. vip 제외 인접한 두 좌석끼리 교환할 수 있다.
 * 2. 연속된 k명이 앉을 수 있는 경우의 수는 정해져있다!!
 * => dp 이용
 * # logic
 * 1. vip 간 자리를 바꿔앉을 수 있는 사람들이 몇 명 있는지 저장한다. (+ 1 되어있는 상태)
 * 2. N은 최대 40이므로, dp를 N까지 계산한다.
 *    dp[i] = dp[i - 1] + dp[i - 2] 이기 때문에, N의 크기를 고려하여 dp를 계산하자.
 * 3. vip 간 사람 수(k)를 계산하여(앞 뒤 vip 구간 - 1),
 *    구간마다 연속된 k명이 앉을 수 있는 경우의 수를 구해 곱한다.
 */

import java.io.*;

public class B2302 {
    static int N, M;
    static int[] vip, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 1
        vip = new int[M + 2];
        for (int i = 1; i <= M; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }
        vip[M + 1] = N + 1;

        // 2. dp : 연속된 i명이 바꿔 앉을 수 있는 경우의 수
        dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (N > 1) {
            dp[2] = 2;
            for (int i = 3; i <= N; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        // 3. 합 출력
        int sum = 1;
        for (int i = 1; i <= M + 1; i++) {
            int people = vip[i] - vip[i - 1] - 1;
            sum *= dp[people];
        }
        System.out.println(sum);
    }
}