// https://school.programmers.co.kr/learn/courses/30/lessons/161990
package com.programmers.aug_2025;

import java.util.Arrays;

/**
 * 161990: 바탕화면 정리
 * # summary
 * : 한 번의 드래그로 최소한의 이동거리를 갖는 시작점과 끝점을 구하자.
 * # access
 * 1. 최소 이동거리를 가지려면 '#'이 있는 곳 중 왼쪽 상단 ~ 오른쪽 하단을 드래그해야 한다.
 * => '#'이 나오는 가장 최소의 x, y값과 최대의 x, y 값을 구하자.
 * # logic
 * 1. 시작점은 최소의 x, y값을 가져야 하므로 Max값을, 끝점은 최대의 x, y값을 가져야 하므로 Min값으로 초기화한다.
 * 2. wallpaper 위 모든 점을 탐색하여 '#'이 있다면
 *     2-1. 최소 x, y와 탐색 대상인 x, y 를 비교하여 작은 값으로 answer[0]과 [1]을 갱신하고,
 *     2-2. 최대 x, y와 탐색 대상인 x, y의 우측 하단(x + 1, y + 1)을 비교하여 큰 값으로 answer[2]와 [3]을 갱신한다.
 */

class Solution161990 {
    int N, M;

    public int[] solution(String[] wallpaper) {
        // 1
        int[] answer = new int[4];
        for (int i = 0; i < 2; i++) answer[i] = Integer.MAX_VALUE;
        for (int i = 2; i < 4; i++) answer[i] = 0;
        N = wallpaper.length;
        M = wallpaper[0].length();

        // 2
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (wallpaper[x].charAt(y) == '#') {
                    // 2-1
                    answer[0] = Math.min(answer[0], x);
                    answer[1] = Math.min(answer[1], y);
                    // 2-2
                    answer[2] = Math.max(answer[2], x + 1);
                    answer[3] = Math.max(answer[3], y + 1);
                }
            }
        }

        return answer;
    }
}

public class P161990 {
    public static void main(String[] args) {
        Solution161990 s = new Solution161990();

        System.out.println(Arrays.toString(s.solution(new String[] {".#...", "..#..", "...#."})));
        System.out.println(Arrays.toString(s.solution(new String[] {"..........", ".....#....", "......##..", "...##.....", "....#....."})));
        System.out.println(Arrays.toString(s.solution(new String[] {".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."})));
        System.out.println(Arrays.toString(s.solution(new String[] {"..", "#."})));
    }
}
