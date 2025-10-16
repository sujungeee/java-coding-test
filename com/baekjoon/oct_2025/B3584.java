// https://www.acmicpc.net/problem/3584
package com.baekjoon.oct_2025;

/**
 * 3584: 가장 가까운 공통 조상
 * # summary
 * : 두 노드의 가장 가까운 공통 조상 구하기
 * # access
 * 1. 두 노드의 부모 노드들을 각각 Set에 저장하며, 부모를 따라 올라가는 두 노드가
 *    -> 상대의 부모 노드들에 포함되면 그 노드가 가장 가까운 공통 조상이 됨.
 * 2. 트리는 거슬러 올라가야 하기 때문에, 1차원 배열로 구성
 *    (인덱스 - 자식 노드, 값 - 부모 노드)
 * # logic
 * 1. 두 노드(node1, node2)의 부모 노드들을 저장할 parents1, parents2와 트리 배열을 초기화 및 할당한다.
 * 2. 자기 자신이 가장 가까운 공통 조상(서로가 부모-자식 노드)일 수 있으므로,
 *    현재 주어진 두 노드를 parents에 각각 포함시킨다.
 * 3. 두 노드가 상대의 부모 노드가 저장된 Set에 포함되어 있다면, 공통 조상이므로 답으로 지정하고 break한다.
 * 4. 두 노드가 상대의 부모 노드가 저장된 Set에 포함되어 있지 않다면,
 *    두 노드를 부모 노드로 갱신시키고 & 갱신된 부모 노드를 각 부모 노드가 저장된 Set에 추가한다.
 */

import java.io.*;
import java.util.*;

public class B3584 {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 1
            int answer = 0;
            Set<Integer> parents1 = new HashSet<>();
            Set<Integer> parents2 = new HashSet<>();
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                arr[child] = parent;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 2
            parents1.add(node1);
            parents2.add(node2);

            while (true) {
                // 3
                if (parents1.contains(node2)) {
                    answer = node2;
                    break;
                }
                if (parents2.contains(node1)) {
                    answer = node1;
                    break;
                }
                // 4
                node1 = arr[node1];
                node2 = arr[node2];
                parents1.add(node1);
                parents2.add(node2);
            }

            System.out.println(answer);
        }
    }
}