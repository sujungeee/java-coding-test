// https://www.acmicpc.net/problem/1197
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

public class B1197_2 {
    static int V, E;
    static List<List<int[]>> graph;
    static boolean[] visited;
    static long answer;

    public static void main(String[] args) throws IOException {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = 0;
        graph = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(node1).add(new int[] {weight, node2});
            graph.get(node2).add(new int[] {weight, node1});
            start = node1;
        }
        visited = new boolean[V + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, start});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int weight = cur[0];
            int node = cur[1];
            if (visited[node]) continue;

            visited[node] = true;
            answer += weight;

            for (int[] next : graph.get(node)) {
                if (!visited[next[1]]) {
                    pq.add(next);
                }
            }
        }
        System.out.println(answer);
    }
}