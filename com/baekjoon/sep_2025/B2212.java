// https://www.acmicpc.net/problem/2212
package com.baekjoon.sep_2025;

/**
 * 2212: 센서
 * # summary
 * : 집중국의 수신 가능 영역의 합의 최솟값을 구하자.
 * # access
 * : K개의 수신 영역을 만들려면 (K - 1)개의 구분선이 필요하다.
 * => 구분선은 두 센서 사이의 영역을 의미하므로,
 *    (K - 1)개의 큰 영역은 제외시키고, 나머지 수신 가능 영역을 합산해야 한다.
 * # logic
 * 1. 센서들을 오름차순 정렬한다.
 *    -> 평면상의 직선에 센서들이 차례로 위치해 있기 때문
 * 2. 인접한 두 센서의 차이(영역)를 diffs에 저장한다.
 * 3. diffs를 오름차순하여, (K - 1)개의 큰 영역을 제외할 때까지 수신 가능 영역을 합산한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class B2212 {

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        // 1
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        // 2
        int[] diffs = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diffs[i] = arr[i + 1] - arr[i];
        }

        // 3
        Arrays.sort(diffs);
        for (int i = 0; i < N - K; i++) {
            answer += diffs[i];
        }

        System.out.println(answer);
    }
}