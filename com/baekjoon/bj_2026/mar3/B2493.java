// https://www.acmicpc.net/problem/2493
package com.baekjoon.bj_2026.mar3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B2493 {
    static int N;
    static int[] arr;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = new int[N];
        arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] < arr[i]) {
                stack.pollLast();
            }

            if (!stack.isEmpty()) {
                ans[i] = stack.peekLast() + 1;
            }
            stack.addLast(i);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(ans[i]);
            if (i != N - 1) System.out.print(" ");
        }
    }
}
