package com.programmers.aug_2025;

/**
 * 172928: 공원 산책
 * # summary
 * : 모든 명령을 수행한 후 로봇이 놓인 위치를 구하기
 * # access
 * : 문제 그대로 명령을 수행하기(구현)
 *   단, 수행 도중 명령을 무시할 수 있는 조건에 해당되면 -> 좌표를 원상 복구 시켜놓아야 함!!
 * # logic
 * 1. 로봇의 시작 위치에 도달하면 경로 이동을 시작한다.
 * 2. move(): 명령에 따라 로봇을 이동시켜 최종 좌표를 반환하는 함수
 *     2-1. 주어진 방향에 따라 로봇을 이동시킨다.
 *     2-2. 로봇이 이동할 수 있는 조건이면 로봇을 이동시키고,
 *          로봇이 이동할 수 없는 2가지 조건에 해당되면 로봇의 좌표를 명령 수행 전 좌표로 복구시킨다.
 *     2-3. 명령을 모두 수행한 후, 현재 로봇이 위치한 좌표(x, y)를 반환한다.
 */

import java.util.Arrays;
import java.util.Map;

class Solution172928 {
    Map<String, int[]> dirs = Map.of (
            "E", new int[] {0, 1}
            , "W", new int[] {0, -1}
            , "S", new int[] {1, 0}
            , "N", new int[] {-1, 0}
    );
    int W, H;

    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        W = park.length;
        H = park[0].length();

        for (int i = 0;  i < W; i++) {
            for (int j = 0; j < H; j++) {
                // 1
                if(park[i].charAt(j) == 'S') {
                    answer = move(i, j, park, routes);
                }
            }
        }

        return answer;
    }

    // 2
    public int[] move(int x, int y, String[] park, String[] routes) {
        for (String route : routes) {
            int[] dir = dirs.get(route.charAt(0) + "");
            int cnt = Integer.parseInt(route.charAt(2) + "");
            int oX = x;
            int oY = y;

            for (int i = 0; i < cnt; i++) {
                // 2-1
                x += dir[0];
                y += dir[1];

                // 2-2
                if (x < 0 || x >= W || y < 0 || y >= H || park[x].charAt(y) == 'X') {
                    x = oX;
                    y = oY;
                    break;
                }
            }
        }

        // 2-3
        return new int[] {x, y};
    }
}

public class P172928 {
    public static void main(String[] args) {
        Solution172928 s = new Solution172928();

        System.out.println(Arrays.toString(s.solution(new String[] {"SOO","OOO","OOO"}, new String[] {"E 2","S 2","W 1"})));
        System.out.println(Arrays.toString(s.solution(new String[] {"SOO","OXX","OOO"}, new String[] {"E 2","S 2","W 1"})));
        System.out.println(Arrays.toString(s.solution(new String[] {"OSO","OOO","OXO","OOO"}, new String[] {"E 2","S 3","W 1"})));
    }
}
