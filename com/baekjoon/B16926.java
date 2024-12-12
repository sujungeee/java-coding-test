// https://www.acmicpc.net/problem/16926
// 16926: 배열 돌리기 1
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class B16926 {
    static int N;
    static int M;
    static int[][] arrays;
    static int R;

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        R= Integer.parseInt(st.nextToken());

        arrays= new int[N][M];
        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arrays[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<R; i++){
            rotate();
        }

        for (int[] row : arrays) {
            StringBuilder sb= new StringBuilder();
            for(int i=0; i<M; i++){
                sb.append(row[i]+ " ");
            }
            System.out.println(sb);
        }
    }

    public static void rotate(){
        int rorate_num= Math.min(N, M) / 2;

        // 가장 바깥 사각형의 테두리부터 회전
        for(int i=0; i< rorate_num; i++) {
            int startValue= arrays[i][i];

            // 위쪽: 오른쪽 -> 왼쪽으로 당기기
            for(int j= i; j < M-i-1; j++){
                arrays[i][j]= arrays[i][j+1];
            }

            // 오른쪽: 아래쪽 -> 위쪽으로 당기기
            for(int j=i; j< N-i-1; j++){
                arrays[j][M-i-1]= arrays[j+1][M-i-1];
            }

            // 아래쪽: 왼쪽 -> 오른쪽으로 당기기
            for(int j=M-i-1; j> i; j--){
                arrays[N-i-1][j]= arrays[N-i-1][j-1];
            }

            // 왼쪽: 위쪽 -> 아래쪽으로 당기기
            for(int j=N-i-1; j> i+1; j--){
                arrays[j][i]= arrays[j-1][i];
            }

            arrays[i+1][i]= startValue;
        }
    }
}