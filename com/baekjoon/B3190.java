// https://www.acmicpc.net/problem/3190
// 3190: 뱀
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class B3190 {
    public static int N, K, L;
    public static int[][] apples;
    public static class diff {
        int seconds;
        char dir;

        diff(int seconds, char dir) {
            this.seconds= seconds;
            this.dir= dir;
        }
    }
    public static diff[] infos;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        K= Integer.parseInt(br.readLine());

        apples= new int[K][2];
        for(int i=0; i<K; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            apples[i][0]= Integer.parseInt(st.nextToken());
            apples[i][1]= Integer.parseInt(st.nextToken());
        }

        L= Integer.parseInt(br.readLine());
        infos= new diff[L];
        for(int i=0; i<L; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int seconds= Integer.parseInt(st.nextToken());
            char dir= st.nextToken().charAt(0);
            infos[i]= new diff(seconds, dir);
        }
    }
}
