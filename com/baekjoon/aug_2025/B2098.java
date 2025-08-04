// https://www.acmicpc.net/problem/2098
package com.baekjoon.aug_2025;

/**
 * 2098: 외판원 순회
 * # summary
 * : 모든 도시를 다 돌고 다시 출발지로 돌아오기까지의 최소 비용 구하기
 * # access
 * 1. dp(완전탐색은 O(N!)의 시간복잡도가 소요, 중복된 상태의 값은 메모이제이션으로 값을 가져와야 한다.)
 * 2. 비트마스킹(현재 노드에서의 방문 상태를 하나의 정수로 가지고 있기 위함!)
 * # logic
 * 1. dp[n][k]
 *     1-1. 현재 n번 도시에 있고
 *     1-2. k 상태(비트마스크)만큼 도시를 방문했을 때,
 *     1-3. 아직 방문하지 않은 도시들을 모두 방문하고 출발지(0)로 돌아가는 최소 비용!
 * 2. search()
 *     2-1. 모든 도시를 방문했고, 출발지(0)로 돌아가는 길이 있으면 현재 도시로부터 0번까지의 비용을 return 한다.
 *     2-2. 만약 현재 방문한 도시와 방문 정보에 대해 dp 값을 이미 가지고 있으면 return 한다.
 *     2-3. 그렇지 않으면 새로 값을 할당해야 한다.
 *          현재 방문한 도시로부터 방문하지 않은 다른 도시(i)의 비용이 존재하면
 *          -> (다른 도시로 search() + 다른 도시로의 비용)의 값과 dp 값 중 작은 값을 dp로 갱신한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2098 {
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
        // 2-1
        if (visit == (1 << N) - 1) {
            if (cost[cur][0] == 0) return INF;
            return cost[cur][0];
        }

        // 2-2
        if (dp[cur][visit] != -1) return dp[cur][visit];

        // 2-3
        dp[cur][visit] = INF;
        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && cost[cur][i] != 0) {
                dp[cur][visit] = Math.min(dp[cur][visit], search(i, visit | (1 << i)) + cost[cur][i]);
            }
        }

        return dp[cur][visit];
    }
}