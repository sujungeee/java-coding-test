// https://school.programmers.co.kr/learn/courses/30/lessons/340199
package com.programmers.aug_2025;

/**
 * 340199: 지폐 접기
 * # summary
 * : 지갑에 지폐가 들어갈 때까지 접는 횟수
 * # access
 * : 구현
 * # logic
 * 1. isFit(): 지폐의 가로 크기와 세로 크기가 지갑의 크기보다 작으면 true를 return하는 함수
 * 2. 긴 쪽의 지폐를 반씩 접는다.(/2), 다 접고 접은 횟수 +1
 */

class Solution340199 {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        // 2
        while (!isFit(wallet, bill)) {
            if (bill[0] > bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }
            answer++;
        }

        return answer;
    }

    // 1
    static boolean isFit(int[] wallet, int[] bill) {
        return ((bill[0] <= wallet[0]) && (bill[1] <= wallet[1])) || ((bill[0] <= wallet[1]) && (bill[1] <= wallet[0]));
    }
}

public class P340199 {
    public static void main(String[] args) {
        Solution340199 s = new Solution340199();

        System.out.println(s.solution(new int[] {30, 15}, new int[] {26, 17}));
        System.out.println(s.solution(new int[] {50, 50}, new int[] {100, 241}));
    }
}
