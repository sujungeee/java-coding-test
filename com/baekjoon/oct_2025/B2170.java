// https://www.acmicpc.net/problem/2170
package com.baekjoon.oct_2025;

/**
 * 2170: 선 긋기
 * # summary
 * : 그은 선의 총 길이를 출력하기.
 * # access
 * 1. 겹치는 부분은 한 직선으로 포함시키기!
 * => 겹치는 부분이 없으면, 다른 직선을 새로 시작한다.
 * # logic
 * 1. 직선들(arr)을 오름차순 정렬하여, 차례대로 직선들을 겹쳐야 한다.
 * 2. 한 직선의 시작과 끝을 처음 직선의 x, y로 지정한다.
 * 3. 현재 직선의 시작이 한 직선의 마지막(end) 좌표보다 작다면
 *   -> 선의 범위가 겹치므로, 현재 직선의 마지막과 end 좌표 중 큰 값으로 end를 갱신시켜 더 큰 직선을 만든다.
 * 4. 현재 직선의 시작이 한 직선의 마지막(end) 좌표보다 크면
 *   -> 선의 범위가 겹치지 않으므로, 한 직선의 길이(end - start)를 총 길이(length)에 더하고 & 새롭게 한 직선을 start, end로 재시작한다.
 * 5. 마지막으로 남아있는 직선을 길이로 환산하여 총 길이에 추가한다.
 */

import java.io.*;
import java.util.*;

public class B2170 {
    static int N;
    static long[][] arr;

    public static void main(String[] args) throws IOException {
        long length = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 1
        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        // 2
        long start = arr[0][0];
        long end = arr[0][1];
        for (int i = 1; i < N; i++) {
            // 3
            if (arr[i][0] <= end) {
                end = Math.max(arr[i][1], end);
            } else { // 4
                length += (end - start);
                start = arr[i][0];
                end = arr[i][1];
            }
        }

        // 5
        length += (end - start);
        System.out.println(length);
    }
}