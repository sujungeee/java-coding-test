// https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
package com.programmers.may_2025;

import java.util.ArrayDeque;

/**
 * 1844: 게임 맵 최단거리
 * # summary
 * : (0, 0)~(n-1, m-1)까지 가는 최단 거리 구하기
 * # logic
 * 1. (0, 0)에서의 distance를 1로 하여 queue를 생성
 * 2. 동서남북으로 뻗어나갈 수 있고 / 벽에 가로막히지 않고 / 방문하지 않은 좌표면
 *  -> distance + 1과 함께, 좌표 갱신
 * 3. (n-1, m-1)에 도착하면 distance 리턴
 * 4. 만약, queue에 아무것도 남아있지 않으면 -1 리턴
 */

class Solution1844 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Info {
        int x, y;
        int distance;

        Info(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static int N, M;
    static ArrayDeque<Info> queue;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;

        return bfs(maps);
    }

    public static int bfs(int[][] maps) {
        boolean[][] visited = new boolean[N][M];
        queue = new ArrayDeque<>();
        queue.add(new Info(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Info info = queue.pollFirst();
            if (info.x == N-1 && info.y == M-1) {
                return info.distance;
            }

            for (int i = 0; i < 4; i++) {
                int newX = info.x + dx[i];
                int newY = info.y + dy[i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                if (maps[newX][newY] == 0) continue;
                if (visited[newX][newY]) continue;

                visited[newX][newY] = true;
                queue.addLast(new Info(newX, newY, info.distance + 1));
            }
        }
        return -1;
    }
}

public class P1844 {
    public static void main(String[] args) {
        Solution1844 s = new Solution1844();

        System.out.println(s.solution(new int[][] {
                {1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}
        }));
        System.out.println(s.solution(new int[][] {
                {1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}
        }));
    }
}