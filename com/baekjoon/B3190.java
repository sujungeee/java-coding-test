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
    public static int[][] diffs;

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
        diffs= new int[L][2];
        for(int i=0; i<L; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            diffs[i][0]= Integer.parseInt(st.nextToken());
            diffs[i][1]= Integer.parseInt(st.nextToken());
        }
    }
}
