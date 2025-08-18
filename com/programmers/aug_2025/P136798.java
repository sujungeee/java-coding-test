// https://school.programmers.co.kr/learn/courses/30/lessons/136798
package com.programmers.aug_2025;

/**
 * 136798: 기사단원의 무기
 * # summary
 * : number까지의 약수의 개수를 구해서 limit 조건을 고려해 필요한 철의 무게를 구하자.
 * # access
 * 1. 포인트는 약수의 개수를 구하는 것!
 * => 소인수분해를 통한 약수의 개수를 구하는 공식 이용하기
 * # logic
 * 1. 1부터 number까지 구한 약수의 개수가 limit 초과이면 power를, 그 아래이면 약수의 개수(cnt)를 철의 무게에 합산한다.
 * 2. divide(): 약수의 개수를 구하는 함수
 *     2-1. num에 대해 소인수분해한다.
 *          num에 대해 나눌 수 있는 수로 나누고, 나눈 수(key)에 대한 지수의 개수(value)를 1 추가한다.
 *     2-2. 나눈 수(key)의 지수의 개수(value)에 1을 더한 값을 약수의 개수(cnt)에 곱한 후, 총 약수의 개수를 return한다.
 */

import java.util.Map;
import java.util.HashMap;

class Solution136798 {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        // 1
        for (int i = 1; i <= number; i++) {
            int cnt = divide(i);
            if (cnt > limit) {
                answer += power;
            } else {
                answer += cnt;
            }
        }

        return answer;
    }

    // 2
    int divide(int num) {
        Map<Integer, Integer> divisors = new HashMap<>();
        // 2-1
        while (num != 1) {
            for (int i = 2; i <= num; i++) {
                if (num % i == 0) {
                    divisors.put(i, divisors.getOrDefault(i, 0) + 1);
                    num /= i;
                    break;
                }
            }
        }

        // 2-2
        int cnt = 1;
        for (int divisor : divisors.keySet()) {
            cnt *= divisors.get(divisor) + 1;
        }
        return cnt;
    }
}

public class P136798 {
    public static void main(String[] args) {
        Solution136798 s = new Solution136798();

        System.out.println(s.solution(5, 3, 2));
        System.out.println(s.solution(10, 3, 2));
    }
}
