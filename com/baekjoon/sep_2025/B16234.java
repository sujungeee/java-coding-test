// https://www.acmicpc.net/problem/16234
package com.baekjoon.sep_2025;

/**
 * 16234: 인구 이동
 * # summary
 * : 인구 이동할 수 있는 일 수를 구하자
 * # access
 * 1. 주어진 방법대로 인구 이동을 하자.
 * => 전에 위치했던 곳과 현재 위치한 곳의 인구를 비교해야 한다. bfs를 적절히 활용하자!
 * # logic
 * 1. 하루마다 인구 이동이 가능한 구역의 개수(cnt)를 확인해야 한다.
 * 2. 만약, cnt가 N * N이면 더 이상 국경을 이동할 수 없으므로 탐색을 중단한다.
 *    인구 이동이 가능하면 일 수(day)를 1 추가한다.
 * 3. searchAndMove(): 공유하는 국경선을 찾아 인구를 이동하는 함수
 *    3-1. 필요한 변수와 큐를 선언 & 초기화한다. (주석 참고)
 *    3-2. search: 이전 나라의 인구 수와 현재 나라의 인구 수의 차이가 L 이상 R 이하라면 (+ 범위 벗어나지 X, 방문하지 X)
 *         -> 연합국 개수 +1, 연합국 인구수 +1, 방문 처리, 탐색 큐와 연합국 큐에 현재 나라 정보를 추가해야 한다.
 *    3-3. move: 시작 좌표(sX, sY)로부터 탐색 가능한 모든 연합국에 대해 나누어진 인구 수(people)를 각각 저장한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class B16234 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, L, R;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        int day = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1
            visited = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        searchAndMove(i, j);
                        cnt++;
                    }
                }
            }
            // 2
            if (cnt == N * N) break;
            day++;
        }

        System.out.println(day);
    }

    // 3
    public static void searchAndMove(int sX, int sY) {
        // 3-1
        int cnt = 1; // 연합국의 개수
        int sum = 0; // 총 인구수
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 열 수 있는 국경선을 찾는 큐
        ArrayDeque<int[]> shares = new ArrayDeque<>(); // 연합국들의 좌표를 저장하는 큐
        visited[sX][sY] = true;
        queue.addLast(new int[] {sX, sY});
        shares.addLast(new int[] {sX, sY});
        sum += arr[sX][sY];

        // 3-2
        while (!queue.isEmpty()) {
            int[] coord = queue.pollFirst();
            int x = coord[0];
            int y = coord[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= N) continue;
                if (visited[newX][newY]) continue;
                int sub = Math.abs(arr[newX][newY] - arr[x][y]);
                if (sub >= L && sub <= R) {
                    cnt++;
                    sum += arr[newX][newY];
                    visited[newX][newY] = true;
                    queue.addLast(new int[] {newX, newY});
                    shares.addLast(new int[] {newX, newY});
                }
            }
        }

        // 3-3
        if (cnt > 1) {
            int people = sum / cnt;
            while (!shares.isEmpty()) {
                int[] nation = shares.pollLast();
                int nationX = nation[0];
                int nationY = nation[1];
                arr[nationX][nationY] = people;
            }
        }
    }
}