// https://school.programmers.co.kr/learn/courses/30/lessons/152996
package com.programmers.oct_2025;

/**
 * 152996: 시소 짝꿍
 * # summary
 * 1. 두 weights을 골라 2, 3, 4 중 하나를 곱한 값이 같거나, 두 값이 같으면 균형을 이룸
 * 2. 균형이 맞는 조합의 개수를 구하자.
 * # access
 * 1. Map을 이용하자.
 * => Map을 통해 같은 값이 몇 개있는지 개수(count)를 세자.
 * 2. 1:1, 2:4, 3:4, 2:3 비율에 맞는 weight가 있는지 확인하자.
 * (나눈 결과를 정확히 하기 위해, double로 나눗셈 진행)
 * # logic
 * 1. weights에서 같은 무게에 대한 개수를 1 추가한다.
 * 2. 무게와 개수를 가지고, 비율을 확인해 총 무게가 같은지 확인한다.
 *     2-1. 같은 무게가 2 이상이면, 서로 짝꿍이 될 수 있으므로 nC2를 진행하여 answer에 추가한다.
 *     2-2. 한 무게에 대한 비율이 2:3인 무게가 counts에 존재하면 그 수만큼 곱한 값을 answer에 추가한다.
 *     2-3. 한 무게에 대한 비율이 3:4인 무게가 counts에 존재하면 그 수만큼 곱한 값을 answer에 추가한다.
 *     2-4. 한 무게에 대한 비율이 1:2(2:4)인 무게가 counts에 존재하면 그 수만큼 곱한 값을 answer에 추가한다.
 * 3. 모든 경우의 수를 출력한다.
 */

import java.util.*;

class Solution152996 {
    Map<Double, Long> counts;

    public long solution(int[] weights) {
        long answer = 0;

        // 1
        counts = new HashMap<>();
        for (double weight : weights) {
            counts.put(weight, counts.getOrDefault(weight, 0L) + 1);
        }

        // 2
        for (Double key : counts.keySet()) {
            // 2-1
            long count = counts.get(key);
            if (count >= 2) { // 1:1
                answer += (count * (count - 1) / 2);
            }
            // 2-2
            if (counts.containsKey(key * 3 / 2)) { // 2:3
                answer += count * counts.get(key * 3 / 2);
            }
            // 2-3
            if (counts.containsKey(key * 4 / 3)) { // 3:4
                answer += count * counts.get(key * 4 / 3);
            }
            // 2-4
            if (counts.containsKey(key * 2)) { // 1:2
                answer += count * counts.get(key * 2);
            }
        }

        return answer;
    }
}

public class P152996 {
    public static void main(String[] args) {
        Solution152996 s = new Solution152996();
        System.out.println(s.solution(new int[] {100, 180, 360, 100, 270}));
    }
}