// https://www.acmicpc.net/problem/17204
// 17204: 죽음의 게임
package com.baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17204 {
    static int answer;
    static int N;
    static int K;
    static int[] arrays;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        K= Integer.parseInt(st.nextToken());

        arrays= new int[N];
        visited= new boolean[N];
        for(int i=0; i<N; i++){
            arrays[i]= Integer.parseInt(br.readLine());
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int idx) {
        if (visited[idx]) {
            answer= -1;
            return;
        }

        if (idx == K) {
            return;
        } else {
            visited[idx]= true;
            answer++;
            dfs(arrays[idx]);
        }
    }
}
