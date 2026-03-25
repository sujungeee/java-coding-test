// https://www.acmicpc.net/problem/1261
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

public class B1261 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;
    static int[][] distances;
    static int[][] map;

    static class Info implements Comparable<Info> {
        int x, y, cost;
        Info(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        public int compareTo(Info other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static void dijkstra() {
        PriorityQueue<Info> heapq = new PriorityQueue<>();
        heapq.add(new Info(0, 0, 0));

        while(!heapq.isEmpty()) {
            Info info = heapq.poll();
            int x = info.x;
            int y = info.y;
            int cost = info.cost;
            if (cost > distances[x][y]) continue;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;

                if (cost + map[newX][newY] < distances[newX][newY]) {
                    distances[newX][newY] = cost + map[newX][newY];
                    heapq.add(new Info(newX, newY, distances[newX][newY]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        distances = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(distances[i], Integer.MAX_VALUE);
        distances[0][0] = 0;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        dijkstra();
        System.out.println(distances[N - 1][M - 1]);
    }
}