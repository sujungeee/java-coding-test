// https://www.acmicpc.net/problem/14567
package com.baekjoon.aug_2025;

/**
 * 14567: 선수과목 (Prerequisite)
 * # summary
 * : 과목 순서들을 보고 -> 각 과목이 몇 번째 순서에서 이수할 수 있는지 출력하자!
 * # access
 * : 입력의 선수 과목(a)가 가지고 있는 선수 과목 + 1 을 후수 과목(b)의 이수 가능 학기로 지정하기
 *   만약 후수 과목의 선수 과목이 여러 개이면 -> 연산된 후수 과목의 값 중 큰 값으로 갱신!
 *  1. 특정 입력에 대해서만 처리하는 방식이므로, 순서의 연속성이 반영되지 않음
 *     ex) 2 -> 3, 1 -> 2 => 1 -> 3 은 반영되지 않음
 *  2. 정렬 후, 위 방식으로 접근하기
 *     이유: 항상 선수 과목(a)보다 후수 과목(b)의 값이 더 크기 때문에,
 *          값이 작은 선수 과목부터 위 접근을 시행하면 -> 순서의 연속성을 모두 반영할 수 있음
 *          ex) 1 -> 2, 2 -> 3 => 1 -> 3 은 반영됨!
 * # logic
 * 1. orders에 [선수 과목, 후수 과목]의 순서 정보를 넣는다.
 * 2. 선수 과목의 값이 작은 순으로 orders를 오름차순 정렬한다.
 * 3. 정렬된 배열을 차례대로 순회하며, 후수 과목의 이수 가능 학기를 갱신한다.
 *    => "선수 과목의 이수 학기 + 1"과 "기존 후수 과목의 이수 학기" 중 큰 값을 후수 과목의 이수 가능 학기로 갱신한다.
 * 4. 모든 과목의 이수를 위한 최소 학기를 차례대로 출력한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14567 {
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

        // 1
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            orders[i][0] = a;
            orders[i][1] = b;
        }

        // 2
        Arrays.sort(orders, (a, b) ->Integer.compare(a[0], b[0]));

        // 3
        for (int i = 0; i < M; i++) {
            int pre = orders[i][0];
            int post = orders[i][1];
            answer[post - 1] = Math.max(answer[post - 1], answer[pre - 1] + 1);
        }

        // 4
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }
}