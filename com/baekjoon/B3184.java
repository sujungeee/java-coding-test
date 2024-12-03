package com.baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Arrays;

// 3184: 양
// https://www.acmicpc.net/problem/3184
public class B3184 {
    static int sheepCnt, wolfCnt;
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx= {0, 0, 1, -1};
    static int[] dy= {1, -1, 0, 0};

    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x= x;
            this.y= y;
        }
    }

    public static void main(String[] args) throws IOException {
        sheepCnt= 0;
        wolfCnt= 0;

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        // 입력
        StringTokenizer st= new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());

        map= new char[R][C];
        visited= new boolean[R][C];
        for(int i=0; i<R; i++){
            map[i]= br.readLine().toCharArray();
        }

        for(int i=0; i<R; i++){
            Arrays.fill(visited[i], false);
        }

        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if (!visited[i][j] && (map[i][j] == 'o' || map[i][j] == 'v')){
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sheepCnt).append(" ").append(wolfCnt);
        System.out.println(sb.toString());
    }

    static void bfs(int x, int y) {
        Queue<Node> que= new ArrayDeque<>();
        que.add(new Node(x, y));
        visited[x][y] = true;
        int tmpSheepCnt= 0;
        int tmpWolfCnt= 0;

        while(!que.isEmpty()){
            Node cur= que.remove();
            if(map[cur.x][cur.y] == 'o'){
                tmpSheepCnt++;
            } else if(map[cur.x][cur.y] == 'v'){
                tmpWolfCnt++;
            }

            for(int i=0; i<4; i++){
                int newx= cur.x + dx[i];
                int newy= cur.y + dy[i];
                if (newx < 0 || newx >= R || newy < 0 || newy >= C){
                    continue;
                }
                if(visited[newx][newy] || map[newx][newy] == '#'){
                    continue;
                }
                visited[newx][newy] = true;
                que.add(new Node(newx, newy));
            }
        }

        if (tmpSheepCnt > tmpWolfCnt){
            sheepCnt+= tmpSheepCnt;
        } else{
            wolfCnt+= tmpWolfCnt;
        }
    }
}