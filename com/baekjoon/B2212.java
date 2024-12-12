// https://www.acmicpc.net/problem/2212
// 2212: 센서
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2212 {
    static int answer;
    static int N;
    static int K;
    static int[] censors;
    static int[] ranges;

    public static void main(String[] args) throws IOException {
        answer= 0;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());
        K= Integer.parseInt(br.readLine());
        StringTokenizer st= new StringTokenizer(br.readLine());
        censors= new int[N];
        ranges= new int[N-1];

        for(int i=0; i<N; i++){
            censors[i]= Integer.parseInt(st.nextToken());
        }

        // 1. 센서 정렬
        Arrays.sort(censors);

        // 2. 센서 간 범위 구하기
        for(int i=0; i<N-1; i++){
            ranges[i]= censors[i+1] - censors[i];
        }

        // 3. 센서 간 범위 정렬
        Arrays.sort(ranges);

        // 4. 범위로 값 구하기
        for(int i=0; i<N-K; i++){
            answer+= ranges[i];
        }
        System.out.println(answer);
    }
}