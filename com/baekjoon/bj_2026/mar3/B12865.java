// https://www.acmicpc.net/problem/12865
package com.baekjoon.bj_2026.mar3;

/**
 * 12865: 평범한 배낭
 * # summary
 * : 물건 무게 합산이 K이하이면서, 최대 가치를 구하자.
 * # access
 * : 브루트포스할라면 시간초과.. dp로 가즈압
 * # logic
 * 1. dp[i] = 무게가 i 이하로 담을 수 있는 최대 가치 -> dp[k] = 무게가 k 이하일 때 최대 가치
 * 3. 점화식: 무게 i가 가지고 있는 최대 가치 <  무게 j가 가지고 있는 가치 + 무게 i - j가 가지고 있는 최대 가치 -> 그걸로 갱신
 *    dp[i] = Math.max(dp[i], 무게 j가 가지고 있는 가치 중 max + dp[i - j])
 */

import java.io.*;
import java.util.*;

public class B12865 {
    static int N, K;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            arr[i][0] = W;
            arr[i][1] = V;
        }

        for (int idx = 1; idx <= N; idx++) {
            int weight = arr[idx][0];
            int value = arr[idx][1];
            for (int i = K; i >= weight; i--) {
                dp[i] = Math.max(dp[i], dp[i - weight] + value);
            }
        }
        System.out.println(dp[K]);
    }
}