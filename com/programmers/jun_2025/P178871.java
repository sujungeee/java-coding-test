// https://school.programmers.co.kr/learn/courses/30/lessons/178871
package com.programmers.jun_2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 178871: 달리기 경주
 * # summary
 * : call로 추월한 플레이어를 고려해 등수를 구하자.
 * # access
 * 1. 일일이 인덱스를 찾아서 하면 시간 복잡도가 초과된다.
 * 2. Hashmap으로 키(이름)-값(등수), 키(등수)-값(이름) 으로 두 map을 생성하고 calling에 따라 등수를 바꿔준다!
 * # logic
 * 1. (player 이름과 등수), (등수와 player 이름)을 가진 두 해시 맵을 만든다.
 * 2. calling에 따라
 *    2-1. 바꿀 플레이어의 등수와 이름을 각각 맵에서 추출(rank1의 등수가 하나 더 적음)
 *    2-2. 각 맵에서 등수와 이름을 바꾸기(추월하기!)
 * 3. 등수에서 차례대로 이름을 꺼내서 answer에 넣기
 */

class Solution178871 {
    Map<String, Integer> info1;
    Map<Integer, String> info2;
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];

        // 1
        info1 = new HashMap<>();
        info2 = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            info1.put(players[i], i + 1);
            info2.put(i + 1, players[i]);
        }

        // 2
        for(String calling : callings) {
            int rank2 = info1.get(calling);
            int rank1 = rank2 - 1;
            String name2 = info2.get(rank2);
            String name1 = info2.get(rank2 - 1);

            info1.put(name2, rank1);
            info1.put(name1, rank2);
            info2.put(rank2, name1);
            info2.put(rank1, name2);
        }

        // 3
        for (int i = 0; i < players.length; i++) {
            answer[i] = info2.get(i + 1);
        }

        return answer;
    }
}

public class P178871 {
    public static void main(String[] args) {
        Solution178871 s = new Solution178871();

        System.out.println(Arrays.toString(s.solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"})));
    }
}