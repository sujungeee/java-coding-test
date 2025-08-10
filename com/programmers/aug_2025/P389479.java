// https://school.programmers.co.kr/learn/courses/30/lessons/389479
package com.programmers.aug_2025;

/**
 * 389479: 서버 증설 횟수
 * # sumamry
 * : 최소 서버 증설 횟수를 구하자.
 * # access
 * : just 구현..?!
 * => k시간 내로 기존 증설 횟수보다 더 많은 이용자 수가 들어오면 서버를 추가로 증설하고 할당 시간을 갱신한다.
 * # logic
 * 1. 현재 시간에서 필요한 서버의 수(cur)가 현 시간에서 운영 중인 서버의 수(running)보다 크면
 *     1-1. 추가로 필요한 서버 수(add)만큼을 총 서버 증설 횟수(answer)에 추가한다.
 *     1-2. 현재 시간으로부터 k시간 동안 -> 추가로 필요한 서버 수를 시간 별 운영 개수를 관리하는 times에 추가한다.
 */

class Solution389479 {
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 총 서버 증설 횟수
        int[] times = new int[24]; // 현 시간에서 운영 중인 서버의 수

        for (int i = 0; i < 24; i++) {
            int player = players[i];
            int cur = player / m; // 필요한 서버의 수
            int running = times[i];

            // 1
            if (cur > running) {
                // 1-1
                int add = cur - running;
                answer += add;
                // 1-2
                for (int j = 0; j < k && (i + j) < 24; j++) {
                    times[i + j] += add;
                }
            }
        }

        return answer;
    }
}

public class P389479 {
    public static void main(String[] args) {
        Solution389479 s = new Solution389479();

        System.out.println(s.solution(new int[] {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5}, 3, 5));
        System.out.println(s.solution(new int[] {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0}, 5, 1));
        System.out.println(s.solution(new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 1, 1));
    }
}
