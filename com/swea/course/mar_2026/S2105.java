// https://swexpertacademy.com/main/code/problem/problemDetail.do
package com.swea.course.mar_2026;

/** N <= 20
 * [summary] 디저트 가장 많이 먹을 수 있는 최대 경로의 수
 * 1) 대각선으로 이동, 제자리로 돌아가기, 왔던 길 그대로 밟아서 돌아오면 안됨
 * 2) 같은 디저트는 먹으면 안됨
 * [idea] 백트래킹(with dfs)
 * - 근데 최대 경로 거치는 어디든 경로는 같을텐데 모든 점 하나하나 다 살펴볼 수 없음..=_=
 * [dfs] (i, j)에서 시작했을 때 가지는 가장 긴 길이....
 */

import java.io.*;
import java.util.*;

public class S2105 {
    static final int[] dx = {1, 1, -1, -1};
    static final int[] dy = {1, -1, -1, 1};
    static int T, N;
    static int[][] arr;
    static Set<Integer> set;
    static int answer;

    static void dfs(int dir, int x, int y, int curX, int curY, boolean[][] visited) {
        if (dir == 3 && x == curX && y == curY) {
            answer = Math.max(answer, set.size());
            return;
        }

        for (int d = dir; d <= dir + 1; d++) {
            if (d == 4) continue;
            int nextX = curX + dx[d];
            int nextY = curY + dy[d];
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

            if (nextX == x && nextY == y) {
                dfs(d, x, y, nextX, nextY, visited);
            } else if (!set.contains(arr[nextX][nextY]) && !visited[nextX][nextY]) {
                set.add(arr[nextX][nextY]);
                visited[nextX][nextY] = true;
                dfs(d, x, y, nextX, nextY, visited);
                set.remove(arr[nextX][nextY]);
                visited[nextX][nextY] = false;
            }
        }


        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            answer = -1;
            // input
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // search
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    boolean[][] visited = new boolean[N][N];
                    set = new HashSet<>();
                    set.add(arr[i][j]);
                    visited[i][j] = true;
                    dfs(0, i, j, i, j, visited);
                }
            }

            System.out.printf("#%d %d\n", testCase, answer);
        }
    }
}