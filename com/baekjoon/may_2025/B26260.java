// https://www.acmicpc.net/problem/26260
// 26260: 이가 빠진 이진 트리
package com.baekjoon.may_2025;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * 26260: 이가 빠진 이진 트리
 * logic
 * @ 1. 받아온 이진 트리를 오름차순으로 정렬
 * @ 2. 재귀 호출로 왼쪽 트리와 오른쪽 트리를 나눠서 호출
 *     , 더 이상 나눌 트리가 없으면(leaf 노드이면) return
 */

public class B26260 {
    static int N;
    static int[] array;
    static int X;
    static int zeroIndex = -1;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == -1) {
                zeroIndex = i;
            }
            array[i] = tmp;
        }
        X = Integer.parseInt(br.readLine());
        array[zeroIndex] = X;

        // 1
        Arrays.sort(array);

        // 2
        recursion(0, N - 1);
        System.out.println(sb.toString());
    }

    private static void recursion(int start, int end) {
        if (start > end) return;
        int mid = (start + end) / 2;
        recursion(start, mid - 1);
        recursion(mid + 1, end);
        sb.append(array[mid]).append(" ");
    }
}