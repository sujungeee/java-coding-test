// https://www.acmicpc.net/problem/14499
package com.baekjoon.oct_2025;

/**
 * 14499: 주사위 굴리기
 * # summary
 * : 주사위를 굴리며 이동시킬 때, 가장 윗 칸의 숫자를 출력하자.
 * # access
 * : 주사위를 남 or 북쪽으로 굴릴 때 / 동 or 서쪽으로 굴릴 때를 나눠서 굴리기.
 * 1) 남 or 북쪽으로 굴리면 전개도에서 세로만 아래/위로 한 칸씩 밀린다.
 * 2) 동 or 서쪽으로 밀리면 전개도에서 1, 6, 3, 4의 위치가 바뀐다.
 * # logic
 * 1. horizontal은 전개도에서 4(서쪽), 1(위쪽), 3(동쪽)을 의미한다.
 *    vertical은 전개도에서 2(뒤쪽), 1(위쪽), 5(앞쪽), 6(바닥)을 의미한다.
 * 2. 주사위를 명령에 따라 새로운 곳으로 옮기는데, 만약 새로운 곳이 바깥이라면 해당 명령을 무시한다.
 * 3. rolling(): 주사위를 명령에 따라 굴리는 함수
 *    3-1. 주사위를 동쪽으로 굴린다면 -> (위 -> 동쪽, 바닥 -> 서쪽, 동쪽 -> 바닥, 서쪽 -> 위)로 주사위가 구른다.
 *    3-2. 주사위를 서쪽으로 굴린다면 -> (위 -> 서쪽, 바닥 -> 동쪽, 동쪽 -> 위, 서쪽 -> 바닥)으로 주사위가 구른다.
 *    3-3. 주사위를 북쪽으로 굴린다면 -> 2, 1, 5, 6이 위로 한 칸씩 밀린다.
 *    3-4. 주사위를 남쪽으로 굴린다면 -> 2, 1, 5, 6이 아래로 한 칸씩 밀린다.
 *    이때, horizontal의 1(위쪽)과 vertical의 1(위쪽)은 같기 때문에 구른 후, 동기화를 시켜주어야 한다.
 * 4. 그 다음, 새로운 곳이 0과 0이 아닐 때의 경우를 나누어 바닥 or 지도에 숫자를 복사한다.
 * 5. 새로운 곳을 x, y로 복사한다.
 * 6. 주사위의 위 칸을 출력한다.
 */

import java.io.*;
import java.util.*;

public class B14499 {
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int N, M, x, y, K;
    static int[][] map;
    static int[] horizontal;
    static int[] vertical;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1
        horizontal = new int[3];
        vertical = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            int dir = Integer.parseInt(st.nextToken());

            // 2. 이동
            int newX = x + dx[dir];
            int newY = y + dy[dir];
            if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;

            // 3. 주사위 갱신
            rolling(dir);

            // 4. 조건
            if (map[newX][newY] == 0) {
                map[newX][newY] = vertical[3];
            } else {
                vertical[3] = map[newX][newY];
                map[newX][newY] = 0;
            }

            // 5. 이동 완
            x = newX;
            y = newY;

            // 6. 위칸 출력
            System.out.println(vertical[1]);
        }
    }

    // 3
    public static void rolling(int dir) {
        if (dir == 1) { // 3-1. 동
            int up = vertical[1];
            int down = vertical[3];
            vertical[1] = horizontal[2];
            vertical[3] = horizontal[0];
            horizontal[0] = up;
            horizontal[2] = down;
        } else if (dir == 2) { // 3-2. 서
            int up = vertical[1];
            int down = vertical[3];
            vertical[1] = horizontal[0];
            vertical[3] = horizontal[2];
            horizontal[0] = down;
            horizontal[2] = up;
        } else if (dir == 3) { // 3-3. 북
            int tmp = vertical[3];
            for (int i = 3; i >= 1; i--) vertical[i] = vertical[i - 1];
            vertical[0] = tmp;
            horizontal[1] = vertical[1];
        } else if (dir == 4) { // 3-4. 남
            int tmp = vertical[0];
            for (int i = 1; i <= 3; i++) vertical[i - 1] = vertical[i];
            vertical[3] = tmp;
            horizontal[1] = vertical[1];
        }
    }
}