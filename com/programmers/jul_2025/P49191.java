// https://school.programmers.co.kr/learn/courses/30/lessons/49191
package com.programmers.jul_2025;

/**
 * 49491: 순위
 * # summary
 * : 정확히 순위를 매길 수 있는 선수의 수 구하기
 * # access
 * 1. 이긴 사람 수 + 진 사람 수가 (n - 1)인 선수가 있으면 순위가 정해짐
 * 2. 한 사람의 이긴 사람 수와 진 사람 수를 구하려면 bfs가 필요하다!
 * # logic
 * 1. 해당 인덱스로부터 이긴 사람들이 존재하는 winInfo, 진 사람들이 존재하는 loseInfo를 등록한다.(by results)
 * 2. 1번부터 n번까지, 특정 사람으로부터 이긴 사람의 수 + 진 사람의 수가 (n - 1)이면 순위가 정해진 것이기 때문에 answer +1
 *    2-1. 이긴 사람의 수와 진 사람의 수는 각각 bfs로 탐색한다.
 */

import java.util.*;

class Solution49191 {
    List<List<Integer>> winInfo;
    List<List<Integer>> loseInfo;

    public int solution(int n, int[][] results) {
        int answer = 0;

        // 1
        winInfo = new ArrayList<>();
        loseInfo = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            winInfo.add(new ArrayList<>());
            loseInfo.add(new ArrayList<>());
        }

        for (int[] result : results) {
            winInfo.get(result[0]).add(result[1]);
            loseInfo.get(result[1]).add(result[0]);
        }

        // 2
        for (int i = 1; i <= n; i++) {
            int winNum = search(i, winInfo, n);
            int loseNum = search(i, loseInfo, n);

            if (winNum + loseNum == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    // 2-1
    public int search(int start, List<List<Integer>> info, int n) {
        int cnt = 0;
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.pollFirst();

            for (int person : info.get(node)) {
                if (visited[person]) continue;

                visited[person] = true;
                queue.addLast(person);
                cnt++;
            }
        }

        return cnt;
    }
}

public class P49191 {
    public static void main(String[] args) {
        Solution49191 s = new Solution49191();

        System.out.println(s.solution(5, new int[][] {
                {4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}
        }));
    }
}