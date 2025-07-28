// https://www.acmicpc.net/problem/20055
package com.baekjoon.jul_2025;

/**
 * 20055: 컨베이어 벨트 위의 로봇
 * # summary
 * : 내구도가 0인 칸이 k개 이상일 때 몇 단계를 거쳤는지 구하기
 * # access
 * : 하나씩 단계를 거치며 직접 구현하기.
 * # logic
 * 1. 세 단계(회전, 이동, 로봇 올리기)를 모두 거치고, 총 단계(answer)를 1 추가한다.
 *    이때, 종료 조건(isFinish())을 만족하면 더 이상 위 세 단계를 진행하지 않고, answer를 return한다.
 * 2. rotate(): 컨베이어 벨트를 회전하는 함수
 *     2-1. 로봇과 칸이 시계 방향으로 한 번씩 회전한다.
 *     2-2. 올리는 위치와 내리는 위치에 로봇이 있으면, 로봇을 내린다.
 * 3. move(): 로봇을 옮기는 함수
 *     3-1. 현재 칸에 로봇이 있고 / 다음 칸에 로봇이 없으며 / 다음 칸의 내구도가 1 이상 남아있는지 확인한다.
 *     3-2. 위의 조건에 모두 해당하면 로봇이 이동하고, 다음 칸의 내구도를 1 깎는다.
 *     3-3. 내리는 위치에 로봇이 있다면, 로봇을 내린다.
 * 4. up(): 첫 칸에 로봇을 올리는 함수
 * 5. isFinish(): 내구도가 0인 칸의 개수가 K개 이상인지 확인하는 함수
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B20055 {
    static int N, K;
    static int[] belt;
    static boolean[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        belt = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) belt[i] = Integer.parseInt(st.nextToken());
        robots = new boolean[2 * N];

        // 1
        int answer = 0;
        while (!isFinish()) {
            rotate();
            move();
            up();
            answer++;
        }

        System.out.println(answer);
    }

    static void rotate() {
        // 2-1
        int lastBelt = belt[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
            robots[i] = robots[i - 1];
        }
        belt[0] = lastBelt;
        // 2-2
        robots[0] = false;
        robots[N - 1] = false;
    }

    static void move() {
        for (int i = N - 2; i >= 0; i--) {
            // 3-1
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
                // 3-2
                robots[i] = false;
                robots[i + 1] = true;
                belt[i + 1]--;
            }
        }
        // 3-3
        robots[N - 1] = false;
    }

    // 4
    static void up() {
        if (belt[0] > 0) {
            robots[0] = true;
            belt[0]--;
        }
    }

    // 5
    static boolean isFinish() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (belt[i] == 0) cnt++;
            if (cnt >= K) return true;
        }
        return false;
    }
}