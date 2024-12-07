package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());

        int[] arr= new int[N+1];
        st= new StringTokenizer(br.readLine());
        arr[1]= Integer.parseInt(st.nextToken());
        for (int i= 2; i<N+1; i++){
            arr[i]= arr[i-1] + Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            st= new StringTokenizer(br.readLine());
            int start= Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            System.out.println(arr[end] - arr[start-1]);
        }
    }
}
