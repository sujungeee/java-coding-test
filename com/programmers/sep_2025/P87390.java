// https://school.programmers.co.kr/learn/courses/30/lessons/87390
package com.programmers.sep_2025;

/**
 * 87390: n^2 배열 자르기
 * # summary
 * : left ~ right의 원소 값을 구하자.
 * # access
 * 1. left ~ right의 1차원 배열을 채우기
 * => 굳이 2차원 배열을 -> 1차원 배열로 바꿀 필요 없음! (몫과 나머지를 활용)
 * 2. n*n인 2차원 배열의 값 = max(i행, j열) + 1
 * 3. 배열의 개수는 long이 아니지만, 배열의 원소는 long이 될 수 있다.
 * # logic
 * left ~ right까지의 i에 대하여,
 * 1. i 인덱스의 행과 열의 위치를 구하자. (행은 몫, 열은 나머지)
 * 2. answer에 차례대로(idx), 2차원 배열의 값인 (행과 열의 값 중 큰 값) + 1을 저장한다.
 */

import java.util.Arrays;

class Solution87390 {
    int[] answer;

    public int[] solution(int n, long left, long right) {
        int iLeft = (int) left;
        int iRight = (int) right;
        answer = new int[iRight - iLeft + 1];

        int idx = 0;
        for (long i = left; i <= right; i++) {
            // 1
            long row = i / n;
            long column = i % n;
            // 2
            answer[idx++] = (int) Math.max(row, column) + 1;
        }

        return answer;
    }
}

public class P87390 {
    public static void main(String[] args) {
        Solution87390 s = new Solution87390();

        System.out.println(Arrays.toString(s.solution(3, 2, 5)));
        System.out.println(Arrays.toString(s.solution(4, 7, 14)));
    }
}
