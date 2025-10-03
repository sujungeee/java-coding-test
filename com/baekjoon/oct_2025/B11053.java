// https://www.acmicpc.net/problem/11053
package com.baekjoon.oct_2025;

/**
 * 11053: 가장 긴 증가하는 부분 수열
 * # summary
 * : 배열 내에 가장 긴 증가하는 부분 수열의 길이를 구하자
 * # access
 * : 다이나믹 프로그래밍!
 * => 앞에 있는 값과 비교하여 그 값이 작다면, 해당 값의 최대 부분 수열 길이에서 +1 하기!
 * # logic
 * 1. dp: 각 값이 가지는 최대 부분 수열 길이
 * 2. 각 값(i)은 적어도 1의 길이를 가진다.
 * 3. 각 값의 앞에 있는 값들을 비교해 앞에 있는 값이 더 적다면,
 *    "그 때의 최대 부분 수열 길이 + 1"과 "현재 값의 최대 부분 수열 길이" 중 더 큰 값으로 갱신한다.
 * 4. 전체 중 가장 긴 부분 수열의 길이를 구하기 위해, 큰 값으로 answer를 갱신한다.
 */

import java.io.*;
import java.util.*;

public class B11053 {
    static int N;
    static int[] arr;
    static int[] dp;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1
        dp = new int[N];
        // 2
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 3
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 4
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}