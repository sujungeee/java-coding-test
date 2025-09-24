// https://www.acmicpc.net/problem/13397
package com.baekjoon.sep_2025;

/**
 * 13397: 구간 나누기 2
 * # summary
 * : 배열을 M개 이하의 구간으로 나누어, (M개 이하의 구간들에서의 최댓값) 중 최솟값을 구하자.
 * # access
 * 1. (M개 이하의 구간들에서의 최댓값) 중 최솟값을 찾기 위해, 이분 탐색을 진행해야 함.
 * # logic
 * 1. 이분 탐색 범위를 지정하기 위해 전체 배열에서의 최솟값(minValue)과 최댓값(maxValue)을 구한 후,
 *    left는 0, right는 전체 구간에서의 (최댓값 - 최솟값)을 지정한다.
 *    (전체 구간이 최댓값과 최솟값의 차이가 가장 클 것이므로!)
 * 2. left ~ right 간 이분탐색을 진행하여, 조건에 만족하는 최솟값을 구한다.
 * 3. possible(): M개 이하의 구간으로 나뉜 배열에서 -> 각 구간에서의 (최댓값 - 최솟값)이 value 이하인지 판별하는 함수
 *    (모든 구간에서의 (최댓값 - 최솟값)이 value 이하여야 최댓값이 value가 된다.)
 *    3-1. 구간의 개수(cnt)와 구간에서의 최솟값(minValue), 최댓값(maxValue)을 초기화한다.
 *    3-2. 구간에서의 (최댓값 - 최솟값)이 value 초과이면 다른 구간이 시작되어야 하므로,
 *         구간의 개수와 구간에서의 최솟값, 최댓값을 갱신한다.
 *    3-3. 구간의 개수가 M개 이하이면 -> 조건을 만족하는 것이므로 true를 반환한다.
 */

import java.io.*;
import java.util.*;

public class B13397 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1
        int minValue = Integer.MAX_VALUE;
        int maxValue = 0;
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);
        }
        int left = 0;
        int right = maxValue - minValue;

        // 2
        while (left <= right) {
            int mid = (left + right) / 2;
            if (possible(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    // 3
    public static boolean possible(int value) {
        // 3-1
        int cnt = 1;
        int minValue = arr[0];
        int maxValue = arr[0];

        for (int i = 0; i < N; i++) {
            minValue = Math.min(minValue, arr[i]);
            maxValue = Math.max(maxValue, arr[i]);
            // 3-2
            if (maxValue - minValue > value) {
                cnt++;
                minValue = arr[i];
                maxValue = arr[i];
            }
        }

        // 3-3
        return cnt <= M;
    }
}