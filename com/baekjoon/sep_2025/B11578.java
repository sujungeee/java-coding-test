// https://www.acmicpc.net/problem/11578
package com.baekjoon.sep_2025;

/**
 * 11578: 팀원 모집
 * # summary
 * : 모든 문제를 맞히기 위한 최소한의 팀원 수
 * # access
 * 1. 완전 탐색
 * => 몇 명이 문제를 풀었는지를 비트마스킹으로 확인
 * # logic
 * 1. total: 팀원들이 문제를 풀었는지에 대한 모든 경우의 수
 * 2. 특정 경우의 수에 대해, 어느 팀원(j)이 문제를 풀었다면,
 *    문제를 푼 사람의 수(cnt)를 1 추가하고, set에 어떤 문제를 풀었는지 추가한다.
 * 3. 만약 특정 경우의 수에서 모든 문제를 다 풀었으면, 문제를 푼 사람의 수를 적게 갱신한다.
 * 4. 모든 문제를 모든 팀원이 풀지 못했으면 -1을 출력하고, 그 외에는 구한 최소한의 팀원 수를 출력한다.
 */

import java.io.*;
import java.util.*;

public class B11578 {
    public static int N, M;
    public static List<Integer>[] arr;
    public static int answer;

    public static void main(String[] args) throws IOException {
        answer = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            arr[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= cnt; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 1
        int total = 1 << M;
        for (int i = 1; i < total; i++) {
            Set<Integer> set = new HashSet<>();
            int cnt = 0;
            // 2
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) != 0) {
                    cnt++;
                    set.addAll(arr[j]);
                }
            }
            // 3
            if (set.size() == N) {
                answer = Math.min(answer, cnt);
            }
        }

        // 4
        answer = (answer != Integer.MAX_VALUE) ? answer : -1;
        System.out.println(answer);
    }
}