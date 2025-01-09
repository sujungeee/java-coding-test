// https://www.acmicpc.net/problem/3190
// 3190: 뱀
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B3190 {
    public static int[] dx= {0, 1, 0, -1}; // 우, 하, 좌, 상
    public static int[] dy= {1, 0, -1, 0};
    public static int answer;
    public static int N, K, L;
    public static int[][] maps;
    public static class diff { // 방향 전환 정보
        int seconds;
        char dir;

        diff(int seconds, char dir) {
            this.seconds= seconds;
            this.dir= dir;
        }
    }
    public static ArrayDeque<diff> infos; // 방향 전환 정보 그룹
    public static ArrayDeque<int []> snakes; // 뱀이 속해있는 좌표 정보 그룹
    public static int dirIdx; // 현재 뱀이 나아가는 방향

    public static void main(String[] args) throws IOException {
        answer= 0;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        K= Integer.parseInt(br.readLine());

        maps= new int[N][N];
        for(int i=0; i<K; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y= Integer.parseInt(st.nextToken());
            maps[x][y]= 1;
        }

        L= Integer.parseInt(br.readLine());
        infos= new ArrayDeque<>();
        for(int i=0; i<L; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int seconds= Integer.parseInt(st.nextToken());
            char dir= st.nextToken().charAt(0);
            infos.addLast(new diff(seconds, dir));
        }

        snakes= new ArrayDeque<>();
        // 머리
        maps[0][0]= 2;
        snakes.addLast(new int[]{0, 0});
        dirIdx= 0; // 오른쪽
        while(true) {
            answer++;
            // 머리 좌표 값 갱신
            int[] head= snakes.peekLast();
            int x= head[0] + dx[dirIdx];
            int y= head[1] + dy[dirIdx];
            System.out.println("새 좌표: x: " + x + ", y: " + y);
            System.out.println("answer: " + answer);

            if (isEdge(x, y) || maps[x][y] == 2) break;

            if (maps[x][y] == 0) { // 사과가 없는 경우: 꼬리 줄이기
                update();
            }

            maps[x][y]= 2;
            snakes.addLast(new int[]{x, y});
            for(int i=0; i<3; i++){
                System.out.println("머리: " + Arrays.toString(snakes.peekLast()));
                System.out.println("꼬리: " + Arrays.toString(snakes.peekFirst()));
            }

            // 방향을 바꾸는 초가 되면 방향 바꾸기
            if (!infos.isEmpty() && answer == infos.peekFirst().seconds) {
                change_dir(infos.pollFirst().dir);
            }
        }

        System.out.println(answer);
    }

    // 머리가 가장자리인지 확인하는 함수
    public static boolean isEdge(int x, int y) {
        return (x >= N || x < 0 || y >= N || y < 0);
    }
    
    //  "새 좌표에 사과가 없는 경우" -> 뱀이 있는 좌표들을 업데이트
    public static void update() {
        int[] tail= snakes.pollFirst();
        maps[tail[0]][tail[1]]= 0;
    }

    // 뱀의 나아가는 방향을 갱신
    public static void change_dir(char newDir) {
        if (newDir == 'L') {
            dirIdx= (dirIdx + 3) % 4;
        } else if (newDir == 'D') {
            dirIdx= (dirIdx + 1) % 4;
        }
    }
}