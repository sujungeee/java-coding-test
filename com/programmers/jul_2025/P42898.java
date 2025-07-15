// https://school.programmers.co.kr/learn/courses/30/lessons/42898
package com.programmers.jul_2025;

/**
 * 42898: 등굣길
 * # summary
 * : 웅덩이를 피해 오른쪽/아래로 이동해서 갈 수 있는 최단 경로의 개수를 1_000_000_007로 나누기
 * # access
 * 1. bfs
 *    => 시간 초과가 난다! O(2^mn), 즉 2^10000
 * 2. dp
 *    2-1. dp[x][y]는 (x, y)로 올 수 있는 최단 경로의 경우의 수
 *    2-2. dp[x][y] = dp[x][y - 1] + dp[x - 1][y]
 *         (왼쪽에서 올 수 있는 경우의 수) + (위에서 올 수 있는 경우의 수)
 *         (단, 물 웅덩이는 무시해야 한다)
 * # logic
 * 1. 물웅덩이는 -1로 표시
 * 2. 출발지로부터 dp[x][y]를 계산
 *    2-1. 만약 출발지이거나 물웅덩이면 pass
 *    2-2. 두 곳이 아니라면 왼쪽과 위에서 올 수 있는 경우의 수를 더해준다.
 *        (왼쪽 or 위가 물웅덩이라면 무시한다(0을 더해준다.))
 */

import java.util.PriorityQueue;

// dp
class Solution42898_2 {
    static int mod = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                if (dp[i][j] == -1) continue;

                int left = dp[i][j - 1] != -1 ? dp[i][j - 1] : 0;
                int up = dp[i - 1][j] != -1 ? dp[i - 1][j] : 0;

                dp[i][j] = (left + up) % mod;
            }
        }

        return dp[n][m];
    }
}

// bfs.. 더미코드..
class Solution42898_1 {
    static int mod = 1_000_000_007;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static int answer;
    static int[][] map;
    static int[][] puds;

    public static class Info implements Comparable<Info>{
        int x, y, distance;

        Info(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int compareTo(Info other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public int solution(int m, int n, int[][] puddles) {
        answer = 0;
        map = new int[n + 1][m + 1];
        puds = puddles;

        bfs(n, m);

        return answer;
    }

    boolean isPuddle(int x, int y) {
        for (int[] puddle : puds) {
            if (y == puddle[0] && x == puddle[1]) return true;
        }
        return false;
    }

    void bfs(int n, int m) {
        int minDistance = Integer.MAX_VALUE;
        PriorityQueue<Info> pQue = new PriorityQueue<>();
        pQue.add(new Info(1, 1, 0));

        while(true) {
            Info i = pQue.poll();
            if (i != null) {
                if (i.x == n && i.y == m) {
                    if (i.distance < minDistance) {
                        minDistance = i.distance;
                        answer = 1;
                    } else if (i.distance == minDistance) {
                        answer++;
                        answer %= mod;
                    } else {
                        break;
                    }
                }

                for (int j = 0; j < 2; j++) {
                    int newX = i.x + dx[j];
                    int newY = i.y + dy[j];

                    if (newX > n || newX < 0 || newY > m || newY < 0) continue;
                    if (isPuddle(newX, newY)) continue;
                    pQue.add(new Info(newX, newY, i.distance + 1));
                }
            } else {
                break;
            }
        }
    }
}

public class P42898 {
    public static void main(String[] args) {
        Solution42898_2 s = new Solution42898_2();

        System.out.println(s.solution(4, 3, new int[][] {{2, 2}}));
    }
}