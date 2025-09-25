// https://www.acmicpc.net/problem/14503
package com.baekjoon.sep_2025;

/**
 * 14503: 로봇 청소기
 * # summary
 * : 로봇이 청소한 칸의 개수를 구하자.
 * # access
 * 1. 시뮬레이션 그대로 따라하면 된다.
 * (* 주의할 점: 청소한 칸은 벽이 아니다.)
 * # logic
 * 1. 현재 칸이 청소되지 않으면 -> 현재 칸을 청소(2)하고, 청소한 칸의 개수(answer)를 1 늘린다.
 * 2. 현재 칸의 주변 4칸 중 빈 칸(0)이 있는지(isExist) 확인한다.
 *    청소되지 않은 빈 칸이 없는 경우,
 *    2-1. 원 방향 그대로 한 칸 후진할 수 있으면(벽이 아니라면) -> 한 칸 후진한다.
 *    2-2. 원 방향 그대로 한 칸 후진할 수 없으면(벽이면) -> 작동을 중지한다.
 * 3. 청소되지 않은 빈 칸이 있는 경우,
 *    3-1. 반시계 방향으로 90도 회전한다.
 *    3-2. 이후, 변경된 방향으로 전진할 곳이 빈 칸이면 -> 한 칸 전진한다.
 * 4. 청소한 칸의 개수를 출력한다.
 */

import java.io.*;
import java.util.*;

public class B14503 {
    final static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    final static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true) {
            // 1
            if (room[r][c] == 0) {
                room[r][c] = 2;
                answer++;
            }

            // 2
            boolean isExist = false;
            for (int i = 0; i < 4; i++) {
                int newR = r + dx[i];
                int newC = c + dy[i];

                if (room[newR][newC] == 0) {
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                int newR = r - dx[d];
                int newC = c - dy[d];
                if (room[newR][newC] == 1) { // 2-2
                    break;
                } else { // 2-1
                    r = newR;
                    c = newC;
                }
            } else {
                // 3-1
                d = (4 + d - 1) % 4;
                // 3-2
                int newR = r + dx[d];
                int newC = c + dy[d];
                if (room[newR][newC] == 0) {
                    r = newR;
                    c = newC;
                }
            }
        }

        // 4
        System.out.println(answer);
    }
}