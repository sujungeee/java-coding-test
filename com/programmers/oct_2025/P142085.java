// https://school.programmers.co.kr/learn/courses/30/lessons/142085
package com.programmers.oct_2025;

/**
 * 142085: 디펜스 게임
 * # summary
 * : 무적권을 효율적으로 써서, 최대 라운드 수까지 킵고잉~
 * # access
 * 1. 그리디
 * => 자기 병사들을 모두 소모한 뒤, 무적권을 쓸 라운드를 PriorityQueue로 결정하자.
 * 2. PriorityQueue 사용
 * => 최대힙을 써서 "무적권을 사용하지 않는 가장 큰 적의 수"를 현재 적의 수와 비교하여, 큰 수에 무적권을 쓰자.
 * # logic
 * 1. 남은 병사(n) 수가 현재 적보다 많다면
 *    -> 적을 물리치고,
 *    -> 무적권을 사용하지 않았으므로, 최대힙에 적의 수(e)를 추가한다. (라운드도 추가!)
 * 2. 남은 무적권 수(mujeokCnt)가 0이면 남은 병사도 없고 무적권도 없으므로 탐색을 종료한다.
 * 3. 무적권을 쓰지 않은 적의 수가 최대힙에 존재한다면 가장 큰 수(max)를 꺼낸다.
 *     3-1. max가 현재 적의 수보다 크면 max에 무적권을 쓴다.
 *     3-2. max가 현재 적의 수보다 적으면 현재 적에게 무적권을 쓴다.
 *     3-3. 라운드를 1 추가하고, 무적권의 개수를 1 줄인다.
 *          (최대힙에 무적권을 쓰지 않은 적이 존재하지 않아도, 3-3을 적용해 무적권을 소모해야 함.)
 * 4. 탐색을 종료하고, 진행된 라운드의 수를 출력한다.
 */

import java.util.*;

class Solution {
    PriorityQueue<Integer> noMujeok;

    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        noMujeok = new PriorityQueue<>(Collections.reverseOrder());
        int mujeokCnt = k;

        for (int e : enemy) {
            // 1
            if (n >= e) {
                n -= e;
                noMujeok.add(e);
                round++;
            } else {
                // 2
                if (mujeokCnt == 0) break;
                // 3
                if (!noMujeok.isEmpty()) {
                    int max = noMujeok.poll();
                    // 3-1
                    if (max >= e) { // max에 무적권 쓰기
                        n += max;
                        n -= e;
                        noMujeok.add(e);
                    } else { // 현재 e에 무적권 쓰기
                        // 3-2
                        noMujeok.add(max);
                    }
                }
                // 3-3
                round++;
                mujeokCnt--;
            }
        }
        // 4
        return round;
    }
}

public class P142085 {
}
