// https://www.acmicpc.net/problem/2098
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

/**
 * dp로 풀자
 * 1) dp 정의하기
 * : dp[i][visited] = 최소 비용
 *  i는 번호, j는 시작인 i ~의 방문 경로
 * 2) 초기값 정의
 * - dp[i][visited] = 0
 * - j는 i번째 비트만 1, 나머지는 0으로 세팅
 * 3) 점화식 세우기
 * - i에서의 경로에서의 최소 비용 = k ~ i에서의 최소 비용 + W[k][i]가 더 작으면 그 값으로 갱신
 * - k에서 갈 수 있는 i를 찾고, 다음 노드인 i가 방문하지 않았다면
 * - dp[i][visited | (1 << i)] = dp[k][visited] + W[k][i];
 */

public class B2098 {
    static final int INF = 16_000_000;
    static int N;
    static int[][] W;
    static int[][] dp;
    static int start;

    static int dfs(int cur, int visited) { // 현재 위치에서 시작점까지 남은 여정의 최소 비용
        if (visited == (1 << N) - 1) { // 만약 현재 위치에서 모든 곳을 다 방문했다면
            if (W[cur][start] == 0) {
                return INF;
            } else {
                return W[cur][start];
            }
        }

        if (dp[cur][visited] != -1) { // 현재 위치 방문했다면 더 방문할 필요 X
            return dp[cur][visited];
        }
        dp[cur][visited] = INF;

        for (int i = 1; i <= N; i++) {
            if (W[cur][i] != 0 && (visited & (1 << (i - 1))) == 0) { // 현재 위치(cur)에서 다음(i)으로 갈 수 있고, 다음이 방문 x라면
                int cost = dfs(i, visited | (1 << (i - 1))); // 다음 위치에서 남은 여정의 최소 비용을 구한다.
                dp[cur][visited] = Math.min(dp[cur][visited], cost + W[cur][i]);
            }
        }
        return dp[cur][visited];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 초기값 정의
        dp = new int[N + 1][1 << N];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 2. 점화식
        start = 1;
        int minCost = dfs(1, 1);
        System.out.println(minCost);
    }
}