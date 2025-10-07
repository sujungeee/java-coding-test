package com.baekjoon.oct_2025;

/**
 * 1976: 여행 가자
 * # summary
 * : 여행 계획(plan)을 모두 지킬 수 있으면 YES~, 아님 NO~
 * # access
 * : 각 도시마다 최종적으로 갈 수 있는 도시를 저장하고,
 *   계획에 맞춰서 도시를 방문할 수 있는지 확인한다.
 * # logic
 * 1. search(): 도시 i가 방문할 수 있는 모든 도시를 탐색하는 함수
 *     1-1. 현재 도시는 방문이 가능하므로, queue에 추가 & 방문 처리 & canGo(방문할 수 있는 도시 리스트)에 추가한다.
 *     1-2. 현재 도시에서 방문할 수 있는 다음 도시가 방문하지 않았다면,
 *          역시 ueue에 추가 & 방문 처리 & canGo(방문할 수 있는 도시 리스트)에 추가한다.
 * 2. 현재 도시에서 방문해야 할 다음 도시가 "방문할 수 있는 도시 리스트"에
 *    있다면 ->  계속 다음 도시로 이동, 없다면 isExist를 false로 갱신하고 멈춘다.
 * 3. isExist가 true이면 계획에 따라 도시 이동이 가능하므로 YES, false이면 NO를 출력한다.
 */

import java.io.*;
import java.util.*;

public class B1976 {
    static int N, M;
    static int[][] cities;
    static int[] plan;
    static Map<Integer, List<Integer>> canGo; // <현재 도시, 현재 도시에서 방문할 수 있는 도시 리스트>

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cities = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        plan = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 1
        canGo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            canGo.put(i, new ArrayList<>());
            search(i);
        }

        boolean isExist = true;
        // 2
        for (int i = 0; i < M - 1; i++) {
            int cur = plan[i];
            int next = plan[i + 1];
            if (!canGo.get(cur).contains(next)) {
                isExist = false;
                break;
            }
        }
        // 3
        System.out.println(isExist ? "YES" : "NO");
    }

    static void search(int v) {
        // 1-1
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        queue.add(v);
        visited[v] = true;
        canGo.get(v).add(v);

        while (!queue.isEmpty()) {
            int city = queue.pollFirst();

            for (int i = 0; i < N; i++) {
                // 1-2
                int nextCity = cities[city][i];
                if (nextCity == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    canGo.get(v).add(i);
                }
            }
        }
    }
}