// https://www.acmicpc.net/problem/10472
package com.baekjoon.sep_2025;

/**
 * 10472: 십자뒤집기
 * # summary
 * : 흰색 보드로부터 주어진 입력으로 변환하기까지의 최소 클릭 횟수
 * # access
 * 1. 모든 검은색 칸을 흰색 칸으로 바꾸기까지의 최소 클릭 횟수를 구하자.(정답과 동일)
 * 2. 비트마스킹 + bfs
 * -> 경우의 수가 많지 않기 때문에, 전체를 돌면서 시도하지 않았던 보드의 형태만 queue에 추가하며 탐색한다.
 * # logic
 * 1. 각 3*3 칸이 검은색이면 1이라 칭하고 state에 합산한다.
 * 2. search(): 시작(state)에서 모든 칸이 흰색이 될 때까지 탐색하는 함수
 *     2-1. queue에 보드의 상태(start)와 클릭한 횟수(cnt)를 넣고, visited에 처음 보드의 상태를 true로 바꾼다.
 *     2-2. queue에서 꺼낸 보드의 상태가 모두 흰색(0)이면 -> 클릭한 횟수를 answer로 갱신하고 탐색을 끝낸다.
 *     2-3. 특정 칸과 인접한 칸을 뒤집은 보드의 상태가 방문되지 않았다면 -> 방문하고, queue에 새 보드의 상태를 추가한다.
 * 3. flip(): 특정 칸과 인접한 칸을 뒤집는 함수(검 -> 흰, 흰 -> 검)
 *     3-1. 현재 칸을 뒤집는다.
 *     3-2. 인접한 칸(동서남북)이 3*3 범위 내라면 -> 해당 칸을 뒤집는다.
 */

import java.io.*;
import java.util.*;

public class B10472 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int P;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= P; testCase++) {
            // 1
            int state = 0;
            for (int i = 0; i < 3; i++) {
                char[] tmp = br.readLine().toCharArray();
                for (int j = 0; j < 3; j++) {
                    if (tmp[j] == '*') {
                        state |= (1 << (i * 3 + j));
                    }
                }
            }

            System.out.println(search(state));
        }
    }

    // 3
    public static int flip(int state, int idx) {
        int x = idx / 3;
        int y = idx % 3;
        // 3-1
        state ^= (1 << idx);

        // 3-2
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX < 0 || newX >= 3 || newY < 0 || newY >=3) continue;
            int newIdx = 3 * newX + newY;
            state ^= (1 << newIdx);
        }

        return state;
    }

    // 2
    public static int search(int start) {
        // 2-1
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[1 << 9];
        queue.add(new int[] {start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.pollFirst();
            int state = info[0];
            int cnt = info[1];

            // 2-2
            if (state == 0) return cnt;

            // 2-3
            for (int i = 0; i < 9; i++) {
                int newState = flip(state, i);
                if (!visited[newState]) {
                    visited[newState] = true;
                    queue.add(new int[] {newState, cnt + 1});
                }
            }
        }
        return -1;
    }
}