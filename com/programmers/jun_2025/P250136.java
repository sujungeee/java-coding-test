// https://school.programmers.co.kr/learn/courses/30/lessons/250136
package com.programmers.jun_2025;

import java.util.*;

/**
 * 250136: [PCCP 기출문제] 2번 / 석유 시추
 * # summary
 * : 뚫은 열에 포함되는 석유 덩어리의 양이 가장 많은 양을 return
 * # access
 * 1. 각 열에 해당하는 행이 어디 석유 덩어리에 해당하는지 알아야한다.
 * 2. 따라서 BFS로 좌표에 따른 석유 덩어리의 정보(고유 번호, 넓이)들을 추출하고,
 * 3. 좌표 정보 + 석유 덩어리 정보로 열마다 측정해서 max값 갱신
 * # logic
 * 1. 좌표마다 석유가 존재하고 방문하지 않은 좌표이면 bfs() 호출
 * 2. bfs()
 *    2-1. 석유 덩어리에 포함되는 좌표에 그룹 id를 지정
 *    2-2. bfs()가 닿는 개수는 groupArea에 저장하고, coordMap에 그룹 id와 매핑
 * 3. 석유가 있는 모든 좌표의 그룹 id를 지정하면, calculate() 호출
 * 4. calculate()
 *    4-1. 열마다 행을 확인해서 그룹 id가 한 번도 포함되지 않으면 set에 추가하고 area를 더함.
 *    4-2. 열 단위로 총 area를 측정하며 maxArea보다 크면 갱신
 * 5. maxArea return
 */

class Solution250136 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static class Coord {
        int x, y;
        int groupArea;

        Coord (int x, int y, int groupArea) {
            this.x = x;
            this.y = y;
            this.groupArea = groupArea;
        }
    }
    static int N, M;
    static int[][] coordGroupId; // 각 좌표에 해당하는 그룹 id 배열
    static Map<Integer, Integer> coordMap; // coordMap: 그룹 id - 그룹 area 가 매핑된 HashMap
    static boolean[][] visited;
    static ArrayDeque<Coord> queue;

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        coordGroupId = new int[N][M];
        coordMap = new HashMap<>();

        visited = new boolean[N][M];
        int groupId = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (land[row][col] == 1 && !visited[row][col]) {
                    visited[row][col] = true;
                    bfs(land, row, col, groupId);
                }
                groupId++;
            }
        }

        return calculate(land);
    }

    public void bfs(int[][] land, int row, int col, int groupId) {
        int groupArea = 1;
        queue = new ArrayDeque<>();
        queue.addLast(new Coord(row, col, groupArea));

        while(!queue.isEmpty()) {
            Coord c = queue.pollFirst();
            int x = c.x;
            int y = c.y;
            coordGroupId[x][y] = groupId;

            for (int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
                if (land[newX][newY] == 0) continue;
                if (visited[newX][newY]) continue;

                visited[newX][newY] = true;
                groupArea++;
                queue.addLast(new Coord(newX, newY, groupArea));
            }
        }

        coordMap.put(groupId, groupArea);
    }

    public int calculate(int[][] land) {
        int maxArea = 0;
        for (int col = 0; col < M; col++) {
            int area = 0;
            Set<Integer> groupSets = new HashSet<>();
            for (int row = 0; row < N; row++) {
                if (land[row][col] == 1) {
                    if (!groupSets.contains(coordGroupId[row][col])) {
                        groupSets.add(coordGroupId[row][col]);
                        area += coordMap.get(coordGroupId[row][col]);
                    }
                }
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}

public class P250136 {
    public static void main(String[] args) {
        Solution250136 s = new Solution250136();

        System.out.println(s.solution(new int[][] {
                {0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}
        }));

        System.out.println(s.solution(new int[][] {
                {1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}
        }));
    }
}