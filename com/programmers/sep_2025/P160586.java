package com.programmers.sep_2025;

import java.util.Arrays;

/**
 * 160586: 대충 만든 자판
 * # summary
 * : 각 target의 대한 최소 push 값을 모두 구하자.
 * # access
 * : 각 자판(keymap)에서, 동일한 값(target의 character)의 인덱스가 제일 작은 인덱스를 구해야 한다.
 * (최소로 눌러야 하므로!)
 * => 완전 탐색
 * # logic
 * 1. 각 target에 대해서 최소 push 값을 search한다.
 * 2. search()
 *     2-1. target를 분할한 각 문자열과 자판에서의 문자열 값이 같으면
 *          -> 해당 인덱스의 값을 최소 인덱스와 비교하여 작은 값으로 갱신하고, 탐색을 멈춘다.
 *     2-2. 만약 target의 분할 문자열에서 자판과 같은 값을 찾지 못했다면, -1을 return한다.
 *     2-3. 값을 찾았다면 누른 개수(cnt)를 합산하고, 총 개수를 return한다.
 */

class Solution160586 {
    int N;

    public int[] solution(String[] keymap, String[] targets) {
        N = targets.length;
        int[] answer = new int[N];

        // 1
        for (int i = 0; i < N; i++) {
            String target = targets[i];
            answer[i] = search(keymap, target);
        }

        return answer;
    }

    // 2
    public int search(String[] keymap, String target) {
        int cnt = 0;

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            int idx = Integer.MAX_VALUE;

            for (String key : keymap) {
                for (int j = 0; j < key.length(); j++) {
                    // 2-1
                    if (key.charAt(j) == c) {
                        idx = Math.min(idx, j + 1);
                        break;
                    }
                }
            }

            // 2-2
            if (idx == Integer.MAX_VALUE) {
                return -1;
            }
            // 2-3
            cnt += idx;
        }

        return cnt;
    }
}

public class P160586 {
    public static void main(String[] args) {
        Solution160586 s = new Solution160586();

        System.out.println(Arrays.toString(s.solution(new String[] {"ABACD", "BCEFD"}, new String[] {"ABCD","AABB"})));
        System.out.println(Arrays.toString(s.solution(new String[] {"AA"}, new String[] {"B"})));
        System.out.println(Arrays.toString(s.solution(new String[] {"AGZ", "BSSS"}, new String[] {"ASA","BGZ"})));
    }
}