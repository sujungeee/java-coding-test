package com.theory.algorithm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 프로그래머스(12978)
// 다익스트라 알고리즘: 시작 점 ~ 모든 점 사이의 최단 거리를 구하고 싶을 때
public class 다익스트라 {
    static PriorityQueue<int[]> queue;
    static int[] distances;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // 1
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        int[][] road = new int[1000][1000];
        for (int[] info : road) { // cost, 목적지
            graph.get(info[0]).add(new int[] {info[2], info[1]});
            graph.get(info[1]).add(new int[] {info[2], info[0]});
        }

        // 2
        dijkstra();
    }

    public static void dijkstra() {
        int start = 1;
        distances[start] = 0;
        // 2-1
        queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        // 2-2
        queue.add(new int[] {0, start});

        while (!queue.isEmpty()) {
            // 2-3
            int[] Node = queue.poll();
            int distance = Node[0];
            int node = Node[1];
            if (distance > distances[node]) continue;

            for (int[] newInfo : graph.get(node)) {
                int nextCost = newInfo[0] + distance;
                int nextNode = newInfo[1];

                // 2-4
                if (nextCost < distances[nextNode]) {
                    // 2-4-1
                    distances[nextNode] = nextCost;
                    // 2-4-2
                    queue.add(new int[] {nextCost, nextNode});
                }
            }
        }
    }
}