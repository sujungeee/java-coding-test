// https://www.acmicpc.net/problem/24023
package com.baekjoon.aug_2025;

/**
 * 24023: 아기 홍윤
 * # summary
 * : 연속된 구간의 or 비트 연산이 K가 되는 구간을 구하기
 * # access
 * : N이 최대 200,000이므로 O(N^2)은 절대 불가능,
 *  => 연속된 구간을 구하는 것이므로 슬라이딩 윈도우로 구간의 앞/뒤를 증가시키며 정해진 조건(==K)을 확인하자!
 * # logic
 * 1. 구간의 합이 K를 초과하면 s가 <0이 되므로 s를 새롭게 할당시켜준다.
 * 2. 마찬가지로 구간의 합이 K를 초과하면 answer, s, e를 초기화한다.
 * 3. 구간의 합이 K를 넘지 않으면
 *     3-1. answer에 현재 인덱스(i)의 배열 값을 or 연산시키고
 *     3-2. 연산 값이 K가 되면 e를 현재 인덱스로 지정하고 break한다.
 * 4. answer가 K이면 연속 된 구간 (s+1, e+1)을 출력하고, 그렇지 않으면 -1을 출력한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B24023 {
    static int N, K;
    static int[] array;

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        for (int i = 0; i < N; i++) {
            // 1
            if (s < 0) {
                s = i;
            }
            // 2
            if ((K | array[i]) > K) {
                answer = 0;
                s = -1;
                e = -1;
            } else { // 3
                // 3-1
                answer = answer | array[i];
                // 3-2
                if (answer == K) {
                    e = i;
                    break;
                }
            }
        }

        // 4
        if (answer == K) {
            System.out.println((s + 1) + " " + (e + 1));
        } else {
            System.out.println(-1);
        }
    }
}