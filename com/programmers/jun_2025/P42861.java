package com.programmers.jun_2025;

import java.util.*;

/**
 * 42861: 섬 연결하기
 * # summary
 * : 최소 비용으로 모든 섬 연결하기
 * # access
 * 1. 모든 섬은 사이클을 이루지 않아야 한다. 즉, 간선의 개수 = 섬의 개수 - 1 이어야 한다. (트리 형식)
 *    이유: 최소 비용을 구해야 하므로
 * 2. 최소 신장 트리(MST)를 사용해야 한다.
 *    => 크루스칼 or 프림 알고리즘
 * # logic(프림 알고리즘)
 * 1. 노드와 간선 정보(Info)를 graph에 저장
 *    , 임의의 노드를 시작 노드(startNode)로 선정
 * 2. prim(): 모든 섬을 다 방문했을 때, 최소 비용을 return 하는 함수
 *    2-1. 시작 노드에서 나가는 간선 + 노드의 정보(info)들을 우선 순위 큐에 넣고, set에 시작 노드를 추가
 *    2-2. 우선 순위 큐에서 가중치가 작은 Info를 꺼내, 기존의 노드와 연결된 새 노드(newNode)가 있는지 확인
 *    2-3. 새 노드가 있으면 그래프(set)에 추가, 가중치도 answer에 추가
 *    2-4. 새 노드에서 나가는 간선 + 노드의 정보(info)들을 우선 순위 큐에 추가
 */

class Solution42861 {
    public static class Info implements Comparable<Info>{
        int node1;
        int node2;
        int cost;

        Info (int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        public int compareTo(Info other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static Queue<Info> pQue; // 노드와 연결된 가중치가 작은 정보부터 poll되는 우선 순위 큐
    static Set<Integer> set; // 그래프에 포함된 노드 정보
    static List<List<Info>> graph; // 노드와 (노드, 연결 노드, 가중치)를 저장한 ArrayList
    static int startNode; // 시작 노드
    static int answer; // 최소 비용

    int solution(int n, int[][] costs) {
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 1
        for (int[] cost : costs) {
            int node1 = cost[0];
            int node2 = cost[1];
            int edge = cost[2];

            graph.get(node1).add(new Info(node1, node2, edge));
            graph.get(node2).add(new Info(node1, node2, edge));
        }
        startNode = costs[0][0];

        // 2
        return prim(n);
    }

    int prim(int n) {
        answer = 0;

        // 2-1
        pQue = new PriorityQueue<>();
        set = new HashSet<>();
        pQue.addAll(graph.get(startNode));
        set.add(startNode);

        while(set.size() < n) {
            // 2-2
            Info info = pQue.poll();
            if (set.contains(info.node1) && set.contains(info.node2)) continue;

            // 2-3
            int newNode = set.contains(info.node1) ? info.node2 : info.node1;
            answer += info.cost;
            set.add(newNode);

            // 2-4
            for (Info next : graph.get(newNode)) {
                if (!set.contains(next)) {
                    pQue.add(next);
                }
            }
        }

        return answer;
    }
}

public class P42861 {
    public static void main(String[] args) {
        Solution42861 s = new Solution42861();

        System.out.println(s.solution(4, new int[][] {
                {0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1},{2, 3, 8}
        }));
    }
}