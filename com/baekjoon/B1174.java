// https://www.acmicpc.net/problem/1174
// 1174: 줄어드는 수
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;

public class B1174 {
    public static int answer;
    public static int N;
    public static ArrayList<Long> results; // 줄어드는 수들이 모여있는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        results= new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dfs(i);
        }
        Collections.sort(results);

        if (results.size() >= N) {
            System.out.println(results.get(N-1));
        } else {
            System.out.println(-1);
        }
    }

    public static void dfs(long num) {
        results.add(num);

        for(int i= (int) (num%10) - 1; i>=0; i--){
            dfs(num*10 + i);
        }
    }
}