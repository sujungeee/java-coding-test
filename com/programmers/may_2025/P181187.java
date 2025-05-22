package com.programmers.may_2025;

public class P181187 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(2, 3));
    }
}

/**
 * 181187: 두 원 사이의 정수 쌍
 * logic
 * @ 4 * (x = 1 ~ r2까지 두 원 사이의 점의 개수)
 */

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        for(int x = 1; x <= r2; x++) {
            long r2Y = (long) Math.floor(Math.sqrt((long)r2*r2 - (long)x*x));
            long r1Y = (long) Math.ceil(Math.sqrt((long)r1*r1 - (long)x*x));
            answer += (r2Y - r1Y + 1);
        }
        return answer * 4;
    }
}