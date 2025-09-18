// https://www.acmicpc.net/problem/2493
package com.baekjoon.sep_2025;

/**
 * 2493: 탑
 * # summary
 * : 각자 탑에서 수신한 탑의 인덱스를 구하자.
 * # access
 * 1. 스택 이용
 * : 스택의 값들은 수신할 수 있는 탑의 위치(인덱스 + 1)여야 한다.
 *   즉, 스택에 있는 탑 이후로 낮은 층의 탑은 제외되어야 한다.
 * # logic
 * 1. 현재 탑보다 낮은 탑들은 스택에서 제외한다.
 * 2. 현재 탑에 가장 가까이 남아있는(수신할) 탑은 스택의 가장 맨 위에 있으므로
 *    -> 가져와(peek) +1 하여 현재 층의 정보(sb)에 추가한다.
 * 3. 현재 탑은 다음 탑의 층 수와 비교해야 하기 때문에, 스택에 자신의 위치를 추가한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class B2493 {
    public static int N;
    public static int[] arr;
    public static ArrayDeque<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // 1
            while (!stack.isEmpty() && arr[stack.peekLast()] < arr[i]) {
                stack.pollLast();
            }

            // 2
            if (!stack.isEmpty()) {
                sb.append(stack.peekLast() + 1).append(" ");
            } else {
                sb.append("0").append(" ");
            }

            // 3
            stack.addLast(i);
        }

        System.out.println(sb);
    }
}