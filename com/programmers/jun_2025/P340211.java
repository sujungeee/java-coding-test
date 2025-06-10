// https://school.programmers.co.kr/learn/courses/30/lessons/340211
package com.programmers.jun_2025;

import java.util.HashMap;
import java.util.Map;

/**
 * 340211: [PCCP 기출문제] 3번 / 충돌위험 찾기
 * # summary
 * : 최단 거리로 움직이는 로봇들이 충돌하는 횟수
 * # access
 * 1. 최단 경로를 이동 -> 다익스트라?
 * 2. 근데 생각해보니깐, 각 좌표는 r을 우선으로 하기 때문에 세로를 먼저 x에 맞추고, 그 다음 y로 맞추면 된다!
 *    즉, 최단 경로로 가는 방식은 정해져있다.(직선으로 이동)
 * # logic
 * 1. 처음 출발지에서도 충돌 위험 횟수를 세기 때문에 출발지에 2개 이상의 로봇이 있으면 answer +1
 *    & 각 로봇에 대해 좌표와 포인트 통과 횟수를 초기화
 * 2. move()
 *   2-1. 경로를 다 이동한 로봇의 개수(finCnt)가 전체 로봇의 개수가 될 때까지 while문 실행
 *   2-2. 로봇들이 한 좌표씩 이동
 *       2-2-1. 구한 목적지 좌표가 포인트에 도달하면 포인트 통과 횟수를 +1 & 새롭게 목적지 설정
 *       2-2-2. 좌표 이동(세로 우선)
 *       2-2-3. 이동한 좌표를 key, 좌표에 도달한 횟수를 value로 해서 map에 저장
 *   2-3. 좌표에 도달한 횟수가 2이상이면 answer +1
 */

class Solution340211 {
    class Route {
        int x, y;
        int cnt;

        Route(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    int answer; // 충돌 위험 횟수
    int robotCnt; // 로봇 수
    int routeLength; // 경로 길이
    Route[] routeProgress; // 로봇들의 경로 정보(좌표, 포인트 통과 횟수)
    int finCnt; // 경로를 다 이동한 로봇의 개수

    public int solution(int[][] points, int[][] routes) {
        answer = 0;
        robotCnt = routes.length;
        routeLength = routes[0].length;
        routeProgress = new Route[robotCnt];
        finCnt = 0;

        // 1
        Map<Integer, Integer> initMap= new HashMap<>();
        for (int i = 0; i < robotCnt; i++) {
            int cnt = initMap.getOrDefault(routes[i][0], 0);
            cnt++;
            initMap.put(routes[i][0], cnt);

            int idx = routes[i][0];
            int x = points[idx - 1][0];
            int y = points[idx - 1][1];
            routeProgress[i] = new Route(x, y, 1);
        }
        for (int key: initMap.keySet()) {
            if (initMap.get(key) >= 2) answer++;
        }

        move(points, routes);

        return answer;
    }

    void move(int[][] points, int[][] routes) {
        // 2-1
        while (finCnt != robotCnt) {
            Map<String, Integer> sameMap = new HashMap<>();

            // 2-2
            for (int i = 0; i < robotCnt; i++) {
                Route r = routeProgress[i];
                if (r.cnt == routeLength) continue;
                int idx = routes[i][r.cnt];
                int destX = points[idx - 1][0];
                int destY = points[idx - 1][1];;

                // 2-2-1
                if (r.x == destX && r.y == destY) {
                    r.cnt++;
                    if (r.cnt == routeLength) {
                        finCnt++;
                        continue;
                    }

                    idx = routes[i][r.cnt];
                    destX = points[idx - 1][0];
                    destY = points[idx - 1][1];;
                }

                // 2-2-2
                if (destX != r.x) {
                    if (r.x > destX) r.x--;
                    else r.x++;
                } else {
                    if (r.y > destY) r.y--;
                    else r.y++;
                }

                // 2-2-3
                String key = r.x + "," + r.y;
                int cnt = sameMap.getOrDefault(key, 0);
                cnt++;
                sameMap.put(key, cnt);
            }

            // 2-3
            for (String key: sameMap.keySet()) {
                if (sameMap.get(key) >= 2) answer++;
            }
        }
    }
}

public class P340211 {
    public static void main(String[] args) {
        Solution340211 s = new Solution340211();

        System.out.println(s.solution(new int[][] {{3, 2}, {6, 4}, {4, 7}, {1, 4}}, new int[][] {{4, 2}, {1, 3}, {2, 4}}));
        System.out.println(s.solution(new int[][] {{3, 2}, {6, 4}, {4, 7}, {1, 4}}, new int[][] {{4, 2}, {1, 3}, {4, 2}, {4, 3}}));
        System.out.println(s.solution(new int[][] {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}}, new int[][] {{2, 3, 4, 5}, {1, 3, 4, 5}}));
    }
}