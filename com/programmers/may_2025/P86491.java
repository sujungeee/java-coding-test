// https://school.programmers.co.kr/learn/courses/30/lessons/86491
package com.programmers.may_2025;

/**
 * 86491: 최소직사각형
 * input: 1 <= sizes <= 10,000
 * logic
 * @ 1. 가로를 큰 값의 모음, 세로를 작은 값의 모음으로 해서
 * @ 2. (가로 모음의 최댓값) * (세로 모음의 최댓값)
 */

class Solution86491 {
    static int maxWidth;
    static int maxHeight;
    public int solution(int[][] sizes) {
        // 1
        for (int[] row : sizes) {
            if (row[0] < row[1]) {
                int tmp = row[0];
                row[0] = row[1];
                row[1] = tmp;
            }
        }

        // 2
        maxWidth = 0;
        maxHeight = 0;
        for (int[] row : sizes) {
            if (row[0] > maxWidth) maxWidth = row[0];
            if (row[1] > maxHeight) maxHeight = row[1];
        }

        return maxWidth * maxHeight;
    }
}

public class P86491 {
    public static void main(String[] args) {
        Solution86491 s = new Solution86491();

        System.out.println(s.solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
        System.out.println(s.solution(new int[][]{{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}}));
        System.out.println(s.solution(new int[][]{{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}));
    }
}