package com.theory.algorithm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 외판원 {
    static final int INF = 16_000_000;
    static int N;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        System.out.println(search(0, 1));
    }

    static int search(int cur, int visit) {
        if (visit == (1 << N) - 1) {
            if (cost[cur][0] == 0) return INF;
            return cost[cur][0];
        }

        if (dp[cur][visit] != -1) return dp[cur][visit];

        dp[cur][visit] = INF;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && cost[cur][i] != 0) {
                dp[cur][visit] = Math.min(dp[cur][visit], search(i, visit | (1 << i)) + cost[cur][i]);
            }
        }
        return dp[cur][visit];
    }
}