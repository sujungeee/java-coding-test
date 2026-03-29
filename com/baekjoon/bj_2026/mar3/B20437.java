// https://www.acmicpc.net/problem/20437
package com.baekjoon.bj_2026.mar3;

/**
 * 20437: 문자열 게임2
 * # summary
 * 1) 특정 문자열 K개를 포함하는 가장 짧은 문자열의 길이
 * 2) 특정 문자열 K개 포함 + 첫글자와 마지막 글자가 같은 가장 긴 문자열의 길이
 * # access
 * - 특정 문자열이 특정되지 않았으므로 map 이용?
 * - 문자열 : [문자열 출현 개수, 문자열 시작 idx]
 * # logic
 * 1. 현재 정보 위치 저장 -> 특정 문자열에 현 위치 정보 저장
 * 2. (가져온 문자열의 출현 개수 + 1)이 K개가 되면(조건을 충족하면)
 *  2-1. 가장 처음 출현 위치와 현재 위치의 격차를 가지고 가장 짧은 문자열의 길이를 갱신한다.
 *  2-2. 가장 처음 출현 위치와 현재 위치의 격차를 가지고 가장 긴 문자열의 길이를 갱신한다.
 * 3. 처음 출현한 위치를 그 다음 위치로 갱신하고, 다시 k - 1개로 조정....
 */

import java.io.*;
import java.util.*;

public class B20437 {
    static int T;
    static char[] str;
    static List<List<Integer>> info;
    static Map<Character, int[]> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            str = br.readLine().toCharArray();
            info = new ArrayList<>();
            for (int i = 0; i <= 26; i++) {
                info.add(new ArrayList<>());
            }
            int K = Integer.parseInt(br.readLine());
            int answer1 = 10000;
            int answer2 = 0;

            map = new HashMap<>();
            for (int i = 0; i < str.length; i++) {
                char c = str[i];
                // 문자열 : [문자열 출현 개수, 문자열 시작 idx]
                int[] cntIdx = map.getOrDefault(c, new int[] {0, 0});
                int cnt = cntIdx[0];
                int idx = cntIdx[1];
                // 1. 현재 위치 정보 저장
                info.get(c - 'a').add(i);
                // 2. 출현개수 + 1이 K개이면
                if (cnt + 1 == K) {
                    int diff = i -  info.get(c - 'a').get(idx) + 1;
                    answer1 = Math.min(answer1, diff);
                    answer2 = Math.max(answer2, diff);
                    idx++;
                } else {
                    cnt++;
                }
                map.put(c, new int[] {cnt, idx});
            }

            StringBuilder sb = new StringBuilder();
            if (answer1 == 10000 || answer2 == 0) {
                sb.append(-1);
            } else {
                sb.append(answer1).append(" ").append(answer2);
            }
            System.out.println(sb.toString());
        }
    }
}