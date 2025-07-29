// https://www.acmicpc.net/problem/1245
package com.baekjoon.jul_2025;

/**
 * 1245: 농장 관리
 * # summary
 * : 산봉우리의 개수를 구하기
 * # access
 * : bfs + 산봉우리 조건 체크하기
 * # logic
 * 1. 특정 좌표가 방문되지 않았으며, 높이를 가지는 경우 -> 특정 좌표가 산봉우리인지 탐색한다.(search())
 * 2. search(): 특정 좌표가 산봉우리인지의 여부를 return하는 함수
 *     2-1. 특정 좌표를 산봉우리(isPeak)로 지정 / 방문 처리 / 큐에 추가한다.
 *     2-2. 주변 좌표가 산봉우리의 높이보다 크면 산봉우리가 아니므로 isPeak를 false로 바꾼다.
 *     2-3. 주변 좌표가 기존 산봉우리의 높이와 같으면 같은 산봉우리 집합이므로 방문하지 않았을 경우 방문 처리 + 큐에 추가한다.
 * 3. 특정 좌표가 산봉우리이면 산봉우리 개수(answer)를 1 추가한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class B1245 {
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1
                if (!visited[i][j] && map[i][j] != 0) {
                    // 3
                    if(search(i, j)) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    // 2
    static boolean search(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        // 2-1
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        boolean isPeak = true;

        while(!queue.isEmpty()) {
            int[] info = queue.pollFirst();

            for (int i = 0; i < 8; i++) {
                int newX = info[0] + dx[i];
                int newY = info[1] + dy[i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                // 2-2
                if (map[newX][newY] > map[info[0]][info[1]]) {
                    isPeak = false;
                }
                // 2-3
                if (map[newX][newY] == map[info[0]][info[1]] && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        return isPeak;
    }
}