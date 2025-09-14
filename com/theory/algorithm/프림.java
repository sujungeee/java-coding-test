package com.theory.algorithm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 프로그래머스(42861)
public class 프림 {
    public static class Info implements Comparable<Info>{
        int node1, node2, cost;

        Info (int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        public int compareTo(Info other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static Queue<Info> pq; // 노드와 연결된 가중치가 작은 정보부터 poll되는 우선 순위 큐
    static Set<Integer> set; // 그래프에 포함된 노드 정보
    static List<List<Info>> graph; // 노드와 (노드, 연결 노드, 가중치)를 저장한 ArrayList
    static int startNode; // 시작 노드
    static int answer; // 최소 비용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[][] costs = new int[1000][1000];
        for (int[] cost : costs) {
            int node1 = cost[0];
            int node2 = cost[1];
            int edge = cost[2];

            graph.get(node1).add(new Info(node1, node2, edge));
            graph.get(node2).add(new Info(node1, node2, edge));
        }

        startNode = costs[0][0];

        // 2
        System.out.println(prim(n));
    }

     static int prim(int n) {
        answer = 0;

        // 2-1
        set = new HashSet<>();
        set.add(startNode);
        pq = new PriorityQueue<>();
        pq.addAll(graph.get(startNode));

        while(set.size() < n) {
            // 2-2
            Info info = pq.poll();
            if (set.contains(info.node1) && set.contains(info.node2)) continue;

            // 2-3
            int newNode = set.contains(info.node1) ? info.node2 : info.node1;
            answer += info.cost;
            set.add(newNode);

            // 2-4
            for (Info next : graph.get(newNode)) {
                if (!set.contains(next)) {
                    pq.add(next);
                }
            }
        }

        return answer;
    }
}