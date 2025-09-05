package com.programmers.sep_2025;

/**
 * 42884: 단속카메라
 * summary
 * : 모든 차량이 단속 카메라에 찍힐 수 있게 하는 최소 카메라 개수를 구하기
 * access
 * 1. 가장 낮은 진출 지점을 기준으로, 더 빨리 진입하는 다른 차량들을 단속 카메라에 함께 찍히게 하자!
 * 2. 단, 더 빨리 진입하는 다른 차량이 진출 지점보다 더 일찍 진출하면 -> 단속 카메라를 하나 더 설치해야 한다.
 * logic
 * 1. 차량을 진출 지점이 낮은 순으로 오름차순 정렬한다.
 * 2. 만약, 진출 지점보다 진입하는 차량이 더 뒤쪽에 있다면
 *    -> 진출 지점을 새로 진입하는 차량의 진출 지점으로 갱신하고,
 *    -> 단속 카메라를 하나 더 설치한다.
 */

import java.util.Arrays;

class Solution42884 {
    public int solution(int[][] routes) {
        int answer = 0;

        // 1
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        // 2
        int arrival = routes[0][1];
        answer = 1;
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > arrival) {
                arrival = routes[i][1];
                answer++;
            }
        }

        return answer;
    }
}

public class P42884 {
    public static void main(String[] args) {
        Solution42884 s = new Solution42884();

        System.out.println(s.solution(new int[][] {
                {-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}
        }));
    }
}