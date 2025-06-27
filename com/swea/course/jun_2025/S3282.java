// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
package com.swea.course.jun_2025;

/**
 * 3282: 0/1 Knapsack
 * # access
 * : dp[i][j] = (1 ~ i)번째 물건을 사용해서 최대 j의 부피를 가질 수 있는 최대 가치
 * => dp[N][K]로 최대 n개의 물건을 사용해서 최대 k의 부피를 가질 수 있는 최대 가치를 구해야 한다.
 * # logic
 * 1. infos의 0번째에는 부피, 1번째에는 가치를 저장한다.
 * 2. dp[i][j] 를 채운다.
 *    dp[i][j] = max(물건 i를 선택하지 않았을 경우의 최대 가치, 물건 i를 선택했을 경우의 최대 가치)
 *             = max(dp[i - 1][j], dp[i - 1][j - 물건 i의 부피] + 물건 i의 부피)
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3282 {
    static int[][] infos;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            dp = new int[N + 1][K + 1];

            // 1
            infos = new int[N + 1][2];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                infos[i][0] = Integer.parseInt(st.nextToken());
                infos[i][1] = Integer.parseInt(st.nextToken());
            }

            // 2
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    if (j >= infos[i][0]) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - infos[i][0]] + infos[i][1]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            System.out.printf("#%d %d\n", testCase, dp[N][K]);
        }
    }
}