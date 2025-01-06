// https://www.acmicpc.net/problem/2344
// 2344: 거울
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class B2344 {
    public static int N, M;
    public static int[][] boxes;
    public static int[] dx= {0, -1, 0, 1}; // 우 상 좌 하
    public static int[] dy= {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        boxes= new int[N+2][M+2];
        for(int i=1; i<N+1; i++) {
            st= new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++) {
                boxes[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        // 1. 가장자리에 숫자 채우기
        int seq= 1;
        for(int i=1; i<N+1; i++) {
            boxes[i][0]= seq;
            seq++;
        }

        for(int i=1; i<M+1; i++){
            boxes[N+1][i]= seq;
            seq++;
        }

        for(int i=N; i>=1; i--) {
            boxes[i][M+1]= seq;
            seq++;
        }

        for(int i=M; i>=1; i--) {
            boxes[0][i]= seq;
            seq++;
        }

        // 2. 1번부터 시작
        int[][] dirs = {
                {1, N, 1, 0},
                {1, M+1, 1, 1},
                {N, 1, -1, 2},
                {M, 1, -1, 3}
        };

        StringBuilder sb= new StringBuilder();
        for (int idx=0; idx<4; idx++) {
            int start = dirs[idx][0]; // 시작점
            int end = dirs[idx][1]; // 끝점
            int d = dirs[idx][2]; // 증가값 또는 감소값
            int dir = dirs[idx][3]; // 방향 (0: 오른쪽, 1: 위, 2: 왼쪽, 3: 아래)

            for (int j = start; j == end; j += d) {
                int x = j + dx[dir]; // 이동 방향에 따라 x 좌표 계산
                int y = j + dy[dir]; // 이동 방향에 따라 y 좌표 계산
                while (!is_edge(x, y)) { // 가장자리에 도달할 때까지 이동
                    x += dx[dir];
                    y += dy[dir];
                    if (boxes[x][y] == 1) { // 거울이 있으면 방향 전환
                        dir = change_dir(dir);
                    }
                }
                sb.append(boxes[x][y]).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    // 현재 위치가 가장자리인지 확인하는 함수
    public static boolean is_edge(int x, int y) {
        return (x == 0 || x == N + 1 || y == 0 || y == M + 1);
    }

    // 상자가 대각선일 때 방향을 바꾸는(반사하는) 함수
    public static int change_dir(int idx) {
        if (idx == 0) {
            return 1;
        } else if (idx == 3) {
            return 2;
        } else if (idx == 2) {
            return 3;
        } else if (idx == 1) {
            return 0;
        }
        return -1;
    }
}