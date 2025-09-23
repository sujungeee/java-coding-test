// https://www.acmicpc.net/problem/3020
package com.baekjoon.sep_2025;

/**
 * 3020: 개똥벌레
 * # summary
 * : (파괴해야 하는 장애물의 최솟값, 최솟값을 가지는 구간의 개수)를 구하자.
 * # access
 * 1. O(N * H)는 시간 초과이다.
 * => 특정 구간에서 석순과 종유석의 개수를 각각 알기 위해 구간(1 ~ H)의 누적합을 이용해야 한다.
 * # logic
 * 1. 입력값이 홀수 순서이면 석순(top)의 높이의 개수를 1 추가하고,
 *    짝수 순서이면 종유석(bottom)의 높이의 개수를 1 추가한다.
 * 2. 오른쪽부터 석순과 종유석의 개수의 누적합을 진행한다.
 *    (top은 앞에서부터 석순의 개수를 알아내고, bottom은 뒤에서부터 종유석의 개수를 알아내기 위함)
 * 3. 높이가 1에서부터 H까지,
 *    3-1. top의 충돌 횟수 + bottom의 충돌 횟수를 각각 구해, 총 충돌 횟수(crash)를 구한다.
 *    3-2. 총 충돌 횟수가 충돌의 최소 횟수보다 작으면
 *         -> 해당 값으로 충돌의 회소 횟수(minCrash)와 최솟값을 가지는 구간의 개수(cnt)를 갱신한다.
 */

import java.io.*;
import java.util.*;

public class B3020 {
    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 1
        int[] bottom = new int[H + 2];
        int[] top = new int[H + 2];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[value]++;
            } else {
                top[value]++;
            }
        }

        // 2
        for (int i = H - 1; i >= 1; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }

        // 3
        int minCrash = Integer.MAX_VALUE; // 파괴해야 하는 장애물의 최솟값
        int cnt = 0; // 최솟값을 가지는 구간의 개수
        for (int h = 1; h <= H; h++) {
            // 3-1
            int crash = bottom[h] + top[H - h + 1];

            // 3-2
            if (crash < minCrash) {
                minCrash = crash;
                cnt = 1;
            } else if (crash == minCrash) {
                cnt++;
            }
        }

        System.out.println(minCrash + " " + cnt);
    }
}