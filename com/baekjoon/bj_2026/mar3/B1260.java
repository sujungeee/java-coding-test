// https://www.acmicpc.net/problem/1260
package com.baekjoon.bj_2026.mar3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B1260 {
    static int N, M, V;
    static List<List<Integer>> cons;
    static boolean[] visited;
    static StringBuilder sb;
    static Deque<Integer> queue;

    static void init() {
        sb = new StringBuilder();
        visited = new boolean[N + 1];
        Arrays.fill(visited, false);
        queue = new ArrayDeque<>();
        queue.add(V);
        visited[V] = true;
    }

    static void search(int edge) {
        visited[edge] = true;
        sb.append(edge).append(" ");

        for (int next : cons.get(edge)) {
            if (!visited[next]) {
                search(next);
            }
        }
    }

    static void dfs() {
        init();
        search(V);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    static void bfs() {
        init();
        while (!queue.isEmpty()) {
            int edge = queue.pollFirst();
            sb.append(edge).append(" ");

            for (int dest : cons.get(edge)) {
                if (!visited[dest]) {
                    visited[dest] = true;
                    queue.addLast(dest);
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        cons = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            cons.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cons.get(start).add(end);
            cons.get(end).add(start);
        }

        for (List<Integer> list: cons) {
            Collections.sort(list);
        }

        dfs();
        bfs();
    }
}