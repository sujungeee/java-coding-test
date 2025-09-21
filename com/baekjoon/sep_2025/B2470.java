// https://www.acmicpc.net/problem/2470
package com.baekjoon.sep_2025;

/**
 * 2470: 두 용액
 * # summary
 * : 특성값의 합이 0에 가장 가까운 두 용액을 구하자
 * # access
 * 1. 두 특성값의 합이 0에 가깝게 하기 위해 정렬과 투 포인터를 사용하자.
 * => 오름차순으로 정렬된 용액 집합에서 투 포인터의 합에 따라 포인터를 움직이기
 * # logic
 * 1. 배열을 오름차순 정렬한다.
 * 2. 투 포인터의 합의 최댓값을 2_000_000_000을 value로 지정하고,
 *    투 포인터를 양 끝으로 지정한다.(p1, p2)
 * 3. 투 포인터가 교차될 때까지 다음을 수행한다.
 *    3-1. 현재 투 포인터의 합(sum)의 절댓값이 기존 0에 가까운 값(value)의 절댓값보다 작으면,
 *         투 포인터의 합을 value로, 해당 용액을 minAnswer과 maxAnswer 값으로 갱신한다.
 *    3-2. 두 포인터의 합이 0보다 크면 오른쪽 포인터를 왼쪽으로,
 *         0보다 작으면 왼쪽 포인터를 오른쪽으로 이동한다.
 */

import java.util.*;
import java.io.*;

public class B2470 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        int minAnswer = 0;
        int maxAnswer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 1
        Arrays.sort(arr);

        // 2
        Long value = 2_000_000_000L;
        int p1 = 0;
        int p2 = N - 1;

        // 3
        while (p1 < p2) {
            Long sum = (long) arr[p1] + arr[p2];

            // 3-1
            if (Math.abs(sum) <= Math.abs(value)) {
                value = sum;
                minAnswer = arr[p1];
                maxAnswer = arr[p2];
            }

            // 3-2
            if (sum > 0) p2--;
            else p1++;
        }

        System.out.println(minAnswer + " " + maxAnswer);
    }
}