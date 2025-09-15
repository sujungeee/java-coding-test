// https://www.acmicpc.net/problem/15989
package com.baekjoon.sep_2025;

/**
 * 15989: 1, 2, 3 더하기 4
 * # summary
 * : 1, 2, 3으로 n을 만들 수 있는 경우의 수를 구하기
 * # access
 * 1. dp!
 * => 1 / 2 / 3을 더해서 n을 만들려면 n - 1, n - 2, n - 3을 만들 수 있는 경우의 수를 살펴봐야 함.
 * # logic
 * 1. dp[i][j] 을 초기화한다.
 *    i는 n, j는 마지막에 더할 1 / 2 / 3을 의미한다.
 * 2. n = 4부터 마지막에 1, 2, 3을 더했을 때
 *    n - 1, n - 2, n - 3 을 만들 수 있는 경우의 수를 각각 더한다. (합이 n이 되기 위해)
 *    n - 1을 만들 수 있는 경우의 수는 최대 1이 포함되도록,
 *    n - 2을 만들 수 있는 경우의 수는 최대 2가 포함되도록,
 *    n - 3을 만들 수 있는 경우의 수는 최대 3을 포함할 수 있도록 dp 점화식을 구성해야 한다.
 * 3. 각 테스트 케이스에 맞게 경우의 수를 더하여서 출력한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class B15989 {
    static int T;
    static List<Integer> testCases;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        testCases = new ArrayList<>();
        for (int i = 1; i <= T; i++) {
            int n = Integer.parseInt(br.readLine());
            N = Math.max(N, n);
            testCases.add(n);
        }

        // 1
        dp = new int[N + 1][4];
        dp[1][1] = dp[2][1] = dp[3][1] = dp[2][2] = dp[3][2] = dp[3][2] = dp[3][3] = 1;

        // 2
        for(int i = 4; i <= N; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        // 3
        for (int testCase : testCases) {
            int answer = 0;
            for (int i = 1; i <= 3; i++) {
                answer += dp[testCase][i];
            }
            System.out.println(answer);
        }
    }
}