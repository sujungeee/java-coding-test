// https://www.acmicpc.net/problem/2138
package com.baekjoon.oct_2025;

/**
 * 2138: 전구와 스위치
 * # summary
 * : 현재 상태에서 만들고자 하는 전구의 상태를 만들기 위해 최소 몇 번의 스위칭이 필요한지!
 * # access
 * 1. 첫 번째 전구를 누르는지 or 안누르는지에 대한 경우의 수를 나누자.
 * 2. 전구의 순서 (i - 1)가 타깃 전구의 순서 (i - 1)와 다르면, i번째 전구를 스위칭해서 (i - 1)의 최종 상태를 결정하자.
 * # logic
 * 1. 각각 첫 번째 전구를 누르면 on, 누르지 않으면 off로 하여 경우의 수를 실행한다.
 * 2. change(): 전구를 앞에서부터 확인하며 스위치하는 함수
 *     2-1. mode가 on이면 첫 번째 전구를 눌러 스위칭하고, 스위칭 개수(p)를 1 늘린다.
 *     2-2. 전구의 순서 (i - 1)가 타깃 전구의 순서 (i - 1)와 다르면,
 *          i번째 전구를 스위칭하고 스위칭 개수를 1 늘린다.
 *     2-3. 최종 전구의 상태가 타깃 전구의 상태와 다르면 -1을, 같으면 스위칭 개수를 반환한다.
 * 3. 두 경우의 수 모두 -1이면 불가능하므로 -1을,
 *    둘 중 하나가 가능하면 가능한 수를,
 *    둘 다 가능하면 그 중 작은 수를 answer로 출력한다.
 */

import java.io.*;
import java.util.*;

public class B2138 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] cur = new int[N];
        int[] target = new int[N];
        String s = br.readLine();
        for (int i = 0; i < N; i++) cur[i] = s.charAt(i) - '0';
        String e = br.readLine();
        for (int i = 0; i < N; i++) target[i] = e.charAt(i) - '0';

        // 1: on, off
        int p1 = change("on", cur.clone(), target);
        int p2 = change("off", cur.clone(), target);

        // 3
        if (p1 == -1 && p2 == -1) System.out.println(-1);
        else if (p1 < p2) {
            if (p1 != -1) System.out.println(p1);
            else System.out.println(p2);
        } else {
            if (p2 != -1) System.out.println(p2);
            else System.out.println(p1);
        }
    }

    // 2
    static int change(String mode, int[] start, int[] end) {
        // 2-1
        int p = 0;
        if (mode.equals("on")) {
            start[0] ^= 1;
            start[1] ^= 1;
            p = 1;
        }
        for (int i = 1; i < N; i++) {
            // 2-2
            if (end[i - 1] != start[i - 1]) {
                for (int j = i - 1; j <= i + 1 && j < N; j++) {
                    start[j] ^= 1;
                }
                p++;
            }
        }
        // 2-3
        if (!Arrays.equals(start, end)) return -1;
        return p;
    }
}
