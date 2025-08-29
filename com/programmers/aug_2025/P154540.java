package com.programmers.aug_2025;

/**
 * 154540: 무인도 여행
 * # summary
 * : 무인도에서 최대 몇일씩 머무를 수 있는지 오름차순 정렬하기
 * # access
 * 1. bfs
 * # logic
 * 1. 무인도를 발견하고, 아직 방문하지 않은 무인도라면 -> search()를 통해 하나의 무인도의 영역을 구한다.
 *    무인도의 영역을 구하면 answers에 추가한다.
 * 2. search(): 하나의 무인도의 영역을 구하는 함수
 * 3. 무인도가 존재하지 않으면 -1 return,
 *    무인도가 존재하면 영역을 기준으로 오름차순 정렬한 후 return한다.
 */

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

class Solution154540 {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    List<Integer> answer;
    int N, M;
    boolean[][] visited;

    public int[] solution(String[] maps) {
        answer = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];

        // 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    answer.add(search(maps, i, j));
                }
            }
        }

        // 3
        if (answer.isEmpty()) return new int[] {-1};
        Collections.sort(answer);
        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 2
    public int search(String[] maps, int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int area = Integer.parseInt(maps[x].charAt(y) + "");
        visited[x][y] = true;
        queue.addLast(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] info = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int newX = info[0] + dx[i];
                int newY = info[1] + dy[i];

                if (newX < 0 || newX >= N || newY < 0 || newY >= M ||
                        visited[newX][newY] ||
                        maps[newX].charAt(newY) == 'X') continue;
                visited[newX][newY]= true;
                queue.addLast(new int[] {newX, newY});
                area += Integer.parseInt(maps[newX].charAt(newY) + "");
            }
        }

        return area;
    }
}

public class P154540 {
    public static void main(String[] args) {
        Solution154540 s = new Solution154540();

        System.out.println(Arrays.toString(s.solution(new String[] {"X591X","X1X5X","X231X", "1XXX1"})));
        System.out.println(Arrays.toString(s.solution(new String[] {"XXX","XXX","XXX"})));
    }
}
