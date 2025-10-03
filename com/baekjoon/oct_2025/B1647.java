// https://www.acmicpc.net/problem/1647
package com.baekjoon.oct_2025;

/**
 * 1647: 도시 분할 계획
 * # summary
 * : 두 마을로 쪼개고, 각 마을 내 최소로 유지비 나가게 하기
 * # access
 * : 전체를 대상으로 최소 신장 트리(MST)를 구성한 후, 가장 큰 유지비를 제외해서 두 마을을 분리하기
 * # logic
 * 1. 집의 출발과 도착을 -> 부모와 자식 관계로 형성하기 위해 rel을 초기화한다.
 * 2. 유지비가 작은 간선부터 탐색하기 위해 우선순위 큐에 Info(두 집 u, v와 경로의 길이)를 추가한다.
 * 3. search(): 유지비가 작은 간선부터 꺼내 집들을 연결하는 함수
 *     3-1. 간선의 개수(edge)가 N-1이 될 때까지
 *     3-2. Info 정보를 꺼내 두 집이 연결(union)될 수 있는지 확인한다.
 *          연결이 가능하면 전체 유지비(total)에 현재 cost를 더하고, 간선의 개수를 하나 추가한다.
 *          마지막의 유지비를 저장하기 위해서 max도 갱신한다.
 *     3-3. 전체 유지비(total)에서 마지막 유지비(max)를 빼 마을을 2개로 분리한다.
 * 4. union(): 두 집의 조상이 다르면 두 집을 부모 - 자식으로 연결하는 함수
 * 5. find(): 한 집의 조상을 찾는 함수, 인덱스와 값이 같은 값이 조상이다.
 */

import java.io.*;
import java.util.*;

public class B1647 {
    public static class Info implements Comparable<Info> {
        int u, v, cost;
        Info (int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        public int compareTo(Info other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    public static int N, M;
    public static int[] rel;
    public static PriorityQueue<Info> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1
        rel = new int[N + 1];
        for (int i = 1; i <= N; i++) rel[i] = i;

        // 2
        queue = new PriorityQueue<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            queue.add(new Info(u, v, cost));
        }

        System.out.println(search());
    }

    // 5
    public static int find(int num) {
        if (rel[num] == num) return num;
        return rel[num] = find(rel[num]);
    }

    // 4
    public static boolean union(int num1, int num2) {
        int pa1 = find(num1);
        int pa2 = find(num2);
        if (pa1 != pa2) {
            rel[pa2] = pa1;
            return true;
        }
        return false;
    }

    // 3
    public static int search() {
        int total = 0;
        int max = 0;
        int edge = 0;

        // 3-1
        while (edge < N - 1) {
            // 3-2
            Info info = queue.poll();
            if (union(info.u, info.v)) {
                total += info.cost;
                max = info.cost;
                edge++;
            }
        }
        // 3-3
        return total - max;
    }
}