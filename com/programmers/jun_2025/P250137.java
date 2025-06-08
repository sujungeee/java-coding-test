// https://school.programmers.co.kr/learn/courses/30/lessons/250137
package com.programmers.jun_2025;

import java.util.Map;
import java.util.HashMap;

/**
 * 250137: [PCCP 기출문제] 1번 / 붕대 감기
 * # summary
 * : 몬스터의 공격을 받은 캐릭터의 총 체력량에 따라 체력 또는 -1을 return
 * # access
 * : 순서(단위: 초)대로 몬스터의 공격이 끝날 때까지 분기 처리 뚜와아ㅏ악.
 * # logic
 * 1. 몬스터의 최대 공격 시간을 구하기.
 *    : 가장 마지막 인덱스에 있다.
 * 2. 몬스터의 공격 시간에 해당하는 피해량을 바로 호출하기 위해 HashMap 사용
 * 3. calculate()
 *    : 최대 공격 시간까지 체력 계산하기
 *    3-1. 몬스터가 공격하면 연속 성공 횟수 초기화 & 피해량 -
 *    3-2. 몬스터가 공격하지 않으면 연속 성공 횟수 + 1 & 초당 회복량 +
 *    3-3. 연속 성공 횟수가 시전 시간에 도달하면 연속 성공 횟수를 초기화 & 추가 회복량 +
 *    3-4. 최대 체력을 넘으면 최대 체력 유지
 *    3-5. 체력이 0이하가 되면, 체력을 회복할 수 없으므로 -1 return
 */

class Solution250137 {
    static int stamina;
    static int attackCnt; // 공격 횟수
    static int maxAttackTime; // 최대 공격 시간
    static Map<Integer, Integer> attacksInfo; // 몬스터 공격 정보
    public int solution(int[] bandage, int health, int[][] attacks) {
        stamina = health;
        attackCnt = attacks.length;
        attacksInfo = new HashMap<>();
        for (int i = 0; i < attackCnt; i++) {
            attacksInfo.put(attacks[i][0], attacks[i][1]);
        }
        maxAttackTime = attacks[attackCnt - 1][0];

        calculate(bandage, health);
        if (stamina <= 0) {
            return -1;
        }
        return stamina;
    }

    void calculate(int[] bandage, int health) {
        int totalSuccess = bandage[0]; // 전체 성공 횟수
        int secRecover = bandage[1]; // 초당 회복량
        int addRecover = bandage[2]; // 추가 회복량
        int contSuccess = 0; // 연속 성공 횟수

        for (int i = 1; i <= maxAttackTime; i++) {
            // 3-1
            if (attacksInfo.containsKey(i)) {
                stamina -= attacksInfo.get(i);
                contSuccess = 0;
            } else {
                // 3-2
                stamina += secRecover;
                contSuccess++;
            }

            // 3-3
            if (contSuccess == totalSuccess) {
                stamina += addRecover;
                contSuccess = 0;
            }

            // 3-4
            if (stamina > health) {
                stamina = health;
            }

            // 3-5
            if (stamina <= 0) {
                stamina = -1;
                break;
            }
        }
    }
}

class Solution250137_2 {
    static int stamina;

    public int solution(int[] bandage, int health, int[][] attacks) {
        stamina = health; // 체력
        int totalSuccess = bandage[0]; // 전체 성공 횟수
        int lastAttackTime = 0; // 마지막 공격 시간
        int secRecover, addRecover; // 초당 회복량, 추가 회복량

        for (int[] attack: attacks) {
            if (stamina <= 0) {
                return -1;
            }

            secRecover = attack[0] - lastAttackTime - 1;
            addRecover = secRecover / totalSuccess;
            lastAttackTime = attack[0];

            stamina = Math.min(health, stamina + (secRecover * bandage[1]));
            stamina = Math.min(health, stamina + (addRecover * bandage[2]));
            stamina -= attack[1];
        }

        return stamina <= 0 ? -1 : stamina;
    }
}

public class P250137 {
    public static void main(String[] args) {
        Solution250137_2 s = new Solution250137_2();

        System.out.println(s.solution(new int[]{5, 1, 5}, 30, new int[][]{
                {2, 10}, {9, 15}, {10, 5}, {11, 5}
        }));
        System.out.println(s.solution(new int[]{3, 2, 7}, 20, new int[][]{
                {1, 15}, {5, 16}, {8, 6}
        }));
        System.out.println(s.solution(new int[]{4, 2, 7}, 20, new int[][]{
                {1, 15}, {5, 16}, {8, 6}
        }));
        System.out.println(s.solution(new int[]{1, 1, 1}, 5, new int[][]{
                {1, 2}, {3, 2}
        }));
    }
}