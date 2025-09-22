// https://www.acmicpc.net/problem/1967
package com.baekjoon.sep_2025;

/**
 * 1967: 트리의 지름
 * # summary
 * : 트리의 지름을 구하자.
 * # access
 * 1. 1번(루트) 노드에서 가장 먼 노드(node1)를 가져온 뒤, 가장 먼 노드(node1)로부터 가장 먼 노드(node2) 사이의 누적 가중치를 구해야 한다.
 * => by bfs
 * # logic
 * 1. 트리는 양방향 그래프이므로, 부모와 자식에 모두 목적지와 가중치 정보(Edge)를 추가한다.
 * 2. search(): start로부터 가장 먼 노드(node)와 최대 누적 가중치(dist)를 찾을 수 있는 함수
 *     2-1. 현재 간선까지의 누적 거리(edge.weight)가 최대 누적 가중치(dist)보다 더 크면
 *          -> 가장 먼 노드와 최대 누적 가중치를 현재 간선의 목적지 및 누적 가중치로 갱신한다.
 *     2-2. 현재 간선에서 갈 수 있는 인접한 노드들을 가져와 가중치를 합산한 뒤, 큐에 add한다.
 * 3. root 노드(1)에서 갈 수 있는 가장 먼 노드(node1)와 최대 누적 가중치를 가져온다.
 * 4. 가장 먼 노드(node1)에서 갈 수 있는 최대 누적 가중치를 가져와 출력한다.
 */

import java.util.*;
import java.io.*;

public class B1967 {
    static class Edge {
        int end, weight;
        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static int N;
    static List<Edge>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // 1
        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[parent].add(new Edge(child, weight));
            adj[child].add(new Edge(parent, weight));
        }

        // 3
        int[] result = search(1);
        // 4
        int dist = search(result[0])[1];
        System.out.println(dist);
    }

    // 2
    static int[] search(int start) {
        int node = start;
        int dist = 0;

        ArrayDeque<Edge> queue = new ArrayDeque<>();
        queue.add(new Edge(start, 0));
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        while (!queue.isEmpty()) {
            Edge edge = queue.pollFirst();

            // 2-1
            if (edge.weight > dist){
                dist = edge.weight;
                node = edge.end;
            }

            // 2-2
            for (Edge newEdge : adj[edge.end]) {
                if (!visited[newEdge.end]) {
                    visited[newEdge.end] = true;
                    queue.add(new Edge(newEdge.end, edge.weight + newEdge.weight));
                }
            }
        }

        return new int[] {node, dist};
    }
}