// https://www.acmicpc.net/problem/1931
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

/**
 * 한 회의실 안에 가장 많은 회의 개수를 잡기!
 * => 그리디 알고리즘
 * -> 회의 개수가 많아질려면?
 * -> 남은 하루의 시간을 길게 잡아둬야 한다.
 * => 빨리 끝나는 회의를 착착착 붙인다.
 *    회의 시간이 어떻든 빨리 끝나면 회의를 더 잡을 수 있는 기회이기 때무네
 * "현재의 선택이 미래에 남겨줄 '시간적 여유'를 최대로 만드는 최선의 작업". ...
 */

public class B1931 {
    static int N;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new int[] {start, end});
        }

        Collections.sort(list, (a, b) -> {
            if (a[1] == b[1]) { return Integer.compare(a[0], b[0]); }
            return Integer.compare(a[1], b[1]);
        });

        int maxTime = list.get(N - 1)[1];
        int finTime = list.remove(0)[1];
        int maxCnt = 1;

        while (finTime <= maxTime && !list.isEmpty()) {
            int[] info = list.remove(0);
            int start = info[0];
            int end = info[1];
            if (finTime <= start) {
                finTime = end;
                maxCnt++;
            }
        }
        System.out.println(maxCnt);
    }
}