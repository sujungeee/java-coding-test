// https://www.acmicpc.net/problem/17070
package com.baekjoon.oct_2025;

/**
 * 17070: 파이프 옮기기 1
 * # summary
 * : 벽을 피해 가로 or 세로 or 대각으로 파이프를 (N, N)까지 옮길 수 있는 경우의 수를 구하기
 * # access
 * 1. 파이프의 머리만 좌표를 옮겨주면 된다. 어차피 꼬리는 머리로 이동할 것이기 때문이다..
 * 2. 대각으로 이동할 떄에는, 머리의 우측, 아래, 대각을 모두 확인해야 한다.
 *    (대각으로 이동할 때, 파이프가 3곳에 모두 걸치게 된다. 한마디로 2*2에 모두 파이프가 존재!)
 * # logic
 * 1. 시작인 (0, 1)을 큐에 넣는다. dir은 진행 방향이다.(0: 가로, 1: 세로, 2: 대각)
 * 2. 큐에서 꺼낸 x, y 좌표가 도착지이면 경우의 수를 1 추가한다.
 * 3. 현 진행 방향으로부터 갈 수 있는 방향의 새 좌표가 범위를 벗어나지 않는다면 queue에 추가한다.
 * 4. 만약 가게될 방향이 대각 방향이라면
 *    -> 가로와 세로 부분이 벽에 가로막히지 않았는지 확인한 후에 queue에 추가한다.
 */

import java.io.*;
import java.util.*;

public class B17070 {
    static int[] dx = {0, 1, 1}; // 가로, 세로, 대각
    static int[] dy = {1, 0, 1};
    static int[][] dirs = {{0, 2}, {1, 2}, {0, 1, 2}}; // 가로: 가로,대각 / 세로: 세로, 대각 / 대각: 전부
    static int N, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        search();

        System.out.println(answer);
    }

    public static void search() {
        // 1
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 1, 0}); // x, y, dir

        while (!queue.isEmpty()) {
            // 2
            int[] info = queue.pollFirst();
            int x = info[0];
            int y = info[1];
            int d = info[2];

            if (x == N - 1 && y == N - 1) {
                answer++;
                continue;
            }

            // 3
            for (int dir : dirs[d]) {
                int newX = x + dx[dir];
                int newY = y + dy[dir];
                if (newX >= N || newY >= N) continue;
                if (map[newX][newY] != 1) {
                    // 4
                    if (dir == 2) {
                        if ((x + 1 < N && map[x + 1][y] != 1) && (y + 1 < N && map[x][y + 1] != 1)) {
                            queue.add(new int[] {newX, newY, dir});
                        }
                    } else {
                        queue.add(new int[] {newX, newY, dir});
                    }
                }
            }
        }
    }
}