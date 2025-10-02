// https://www.acmicpc.net/problem/1916
package com.baekjoon.sep_2025;

/**
 * 1916: 최소비용 구하기
 * # summary
 * : 출발지부터 도착지까지의 최소 비용을 구하기
 * # access
 * : 특정 도시 ~ 특정 도시간의 최소 거리이므로 다익스트라 알고리즘을 이용하자.
 * # logic
 * 1. 특정 도시부터 각 도시까지의 최소 거리를 저장하는 distance 배열을 초기화한다.
 * 2. dijkstra(): start부터 각 도시까지의 최소 거리를 구하는 함수
 *     2-1. 자기 자신(start)으로부터의 거리(distance)를 0으로 초기화하고,
 *          우선순위 큐에 (현재 도시, 시작 도시 ~ 현재 도시까지의 최소 거리)를 넣는다.
 *          (현재 도시가 시작 도시이므로, (start, 0)을 넣는다.)
 *     2-2. 우선순위 큐에서 "시작 도시에서 도달할 수 있는 도시"의 정보를 거리가 작은 순서대로 꺼낸다.
 *     2-3. 현재 지점까지의 최소 거리가 저장되어 있는 최소 거리(distance)보다 크면 패스한다.
 *     2-4. 현재 도시까지의 거리 + (현재 도시에서 갈 수 있는 다른 도시까지의 거리)가 다른 도시의 최소 거리보다 작으면,
 *          다른 도시의 최소 거리를 갱신하고, 큐에 다른 도시 정보를 추가한다.
 * 3. end까지의 최소 거리를 출력한다.
 */

import java.io.*;
import java.util.*;

public class B1916 {
    static int N, M, start, end;
    static List<List<int[]>> graph;
    static int[] distance;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[] {v, cost});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 1
        distance = new int[N + 1];
        Arrays.fill(distance, 1_000_000_000);

        dijkstra();

        // 3
        System.out.println(distance[end]);
    }

    // 2
    public static void dijkstra() {
        // 2-1
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        queue.add(new int[] {start, 0});
        distance[start] = 0;

        while (!queue.isEmpty()) {
            // 2-2
            int[] info = queue.poll();
            int node = info[0];
            int cost = info[1];

            // 2-3
            if (cost > distance[node]) continue;

            for (int[] adj : graph.get(node)) {
                int newNode = adj[0];
                int newCost = cost + adj[1];
                // 2-4
                if (newCost < distance[newNode]) {
                    distance[newNode] = newCost;
                    queue.add(new int[] {newNode, newCost});
                }
            }
        }
    }
}