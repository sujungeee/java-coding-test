// https://www.acmicpc.net/problem/16987
// 16987: 계란으로 계란치기
package com.baekjoon;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class B16987 {
    static int answer;
    static int N;
    static int[][] eggs;

    public static void main(String[] args) throws IOException {
        answer= 0;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        eggs= new int[N][2];
        for(int i=0; i<N; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            eggs[i][0]= Integer.parseInt(st.nextToken());
            eggs[i][1]= Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    // idx: 현재 손에 든 계란의 인덱스
    public static void dfs(int idx, int cnt){
        if (idx >= N){
            answer= Math.max(answer, cnt);
            return;
        }

        if (eggs[idx][0] <= 0){
            dfs(idx+1, cnt);
            return;
        }

        boolean is_break= false;
        for (int i=0; i<N; i++){
            if (i == idx || eggs[i][0] <= 0) continue;
            is_break= true;

            eggs[idx][0]-= eggs[i][1];
            eggs[i][0]-= eggs[idx][1];
            dfs(idx+1, cnt + (eggs[idx][0] <= 0 ? 1 : 0) + (eggs[i][0] <= 0 ? 1 : 0));
            eggs[idx][0]+= eggs[i][1];
            eggs[i][0]+= eggs[idx][1];
        }

        if (!is_break){
            dfs(idx+1, cnt);
        }
    }
}
