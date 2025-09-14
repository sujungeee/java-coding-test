package com.theory.algorithm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준(14567)
public class 위상정렬 {
    static int N, M;
    static int[] answer;
    static int[][] orders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[N];
        for (int i = 0; i < N; i++) answer[i] = 1;
        orders = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            orders[i][0] = a;
            orders[i][1] = b;
        }

        Arrays.sort(orders, (a, b) ->Integer.compare(a[0], b[0]));

        for (int i = 0; i < M; i++) {
            int pre = orders[i][0];
            int post = orders[i][1];

            answer[post - 1] = Math.max(answer[post - 1], answer[pre - 1] + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }
}