// https://www.acmicpc.net/problem/1806
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

public class B1806 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = N + 1;
        int subSum = 0; // 부분합
        int left = 0; // 만족하는 왼쪽 경계선
        int right = 0; // 만족하는 오른쪽 경계선
        while (left <= right && right <= N) {
            if (subSum >= S) {
                cnt = Math.min(cnt, right - left);
                subSum -= arr[left];
                if (left + 1 <= right) left++;
            } else {
                if (right != N) subSum += arr[right];
                right++;
            }
        }
        if (cnt == N + 1) { System.out.println(0); }
        else { System.out.println(cnt); }
    }
}