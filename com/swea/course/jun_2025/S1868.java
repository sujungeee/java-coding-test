// https://swexpertacademy.com/main/code/problem/problemDetail.do
package com.swea.course.jun_2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1868: 파핑파핑 지뢰찾기
 * # summary
 * : 지뢰가 없는 모든 칸에 숫자를 표시하기 위한 최소 클릭 횟수!
 * # access
 * 1. N*N 좌표를 훑으면서 근처 8 방향에 0이 있는 좌표를 터뜨린다.
 *  -> 터지는 곳과 근처 8 방향의 숫자를 표시한다.
 *  -> 위 숫자 중 0이 포함되어 있으면, 0으로부터 8 방향의 숫자도 함께 근처 지뢰 숫자를 표시한다.
 * 2. 숫자가 없는 . 칸의 개수를 answer에 추가
 * # logic
 * 1. 지뢰가 없는 . 에게서 주변(8방향)에 지뢰가 몇 개가 있는지 숫자 기입
 * 2. 숫자가 0인 좌표가 만약에 방문하지 않았다면, bfs 돌리기!
 *     2-1. bfs(): 자신이 0이고 주변에 지뢰(*)가 없다면 queue에 추가
 * 3. 지뢰가 아니고 방문하지 않았다면 answer에 추가
 */

public class S1868 {
    static int N;
    static char[][] map;

    static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    static boolean[][] visited;

    public static int solution() {
        int answer = 0;
        visited = new boolean[N][N];

        // 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0; // 주변의 지뢰 개수

                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];

                    if (newX < 0 || newX >= N || newY < 0 || newY >= N) continue;
                    if (map[newX][newY] == '*') cnt++;
                }
                if (map[i][j] != '*') {
                    map[i][j] = (char) (cnt + '0');
                }
            }
        }

        // 2
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == '0') {
                    bfs(i, j);
                    answer++;
                }
            }
        }

        // 3
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != '*') {
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void bfs(int i, int j) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{i, j});
        visited[i][j] = true;

        while(!queue.isEmpty()) {
            int[] coord = queue.pollFirst();
            int x = coord[0];
            int y = coord[1];

            for (int k = 0; k < 8; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];

                if (newX < 0 || newX >= N || newY < 0 || newY >= N) continue;
                if (!visited[newX][newY]) {
                    if (map[x][y] == '0' && map[newX][newY] != '*') {
                        visited[newX][newY] = true;
                        queue.addLast(new int[] {newX, newY});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            System.out.printf("#%d %d\n", testCase, solution());
        }
    }
}