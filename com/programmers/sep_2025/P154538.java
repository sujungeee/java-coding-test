// https://school.programmers.co.kr/learn/courses/30/lessons/154538
package com.programmers.sep_2025;

/**
 * 154538: 숫자 변환하기
 * # summary
 * : x를 y로 변환하기 위한 최소 연산 횟수를 구하기
 * # access
 * 1. 완전탐색 -> 시간 초과!!
 * 2. bfs를 통해 만들 수 있는 수를 늘려가며 최소 연산 횟수를 구하기.
 *    (+ PriorityQueue, HashSet 이용)
 * # logic
 * 1. 연산 결과를 저장하는 set과 (연산 결과, 연산 횟수)를 탐색하는 queue를 초기화한다.
 *    (queue는 연산 횟수가 적은 순서부터 꺼내지는 우선순위 큐이다.)
 * 2. 연산이 가능할 때까지 다음을 수행한다.
 *     2-1. 현재 연산 횟수가 가장 작은 (연산 결과, 연산 횟수)를 꺼내서 연산 결과가 y인지 확인한다.
 *          만약 y와 같다면, 그 때의 연산 횟수가 최소이므로 답을 갱신하고 탐색을 종료한다.
 *     2-2. 현재 연산 결과에 n을 더한 값이 set에 없고, y값보다 같거나 작으면
 *     2-3. 현재 연산 결과에 2를 곱한 값이 set에 없고, y값보다 같거나 작으면
 *     2-4. 현재 연산 결과에 3을 곱한 값이 set에 없고, y값보다 같거나 작으면
 *     -> set에 새 연산 결과를 추가하고, (새 연산 결과, 연산 횟수 + 1)을 queue에 추가한다.
 */

import java.util.*;

class Solution154538 {
    static int answer;
    static Set<Integer> set;
    static PriorityQueue<int[]> queue;

    public int solution(int x, int y, int n) {
        answer = -1;
        search(x, y, n);

        return answer;
    }

    public static void search(int start, int end, int n) {
        // 1
        set = new HashSet<>();
        set.add(start);
        queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        queue.add(new int[] {start, 0});

        // 2
        while (!queue.isEmpty()) {
            // 2-1
            int[] info = queue.poll();
            int x = info[0];
            int cnt = info[1];

            if (x == end) {
                answer = cnt;
                break;
            }

            // 2-2
            if (!set.contains(x + n) && x + n <= end) {
                set.add(x + n);
                queue.add(new int[] {x + n, cnt + 1});
            }
            // 2-3
            if (!set.contains(x * 2) && x * 2 <= end) {
                set.add(x * 2);
                queue.add(new int[] {x * 2, cnt + 1});
            }
            // 2-4
            if (!set.contains(x * 3) && x * 3 <= end) {
                set.add(x * 3);
                queue.add(new int[] {x * 3, cnt + 1});
            }
        }
    }
}

public class P154538 {
}