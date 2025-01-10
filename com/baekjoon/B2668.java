// https://www.acmicpc.net/problem/2668
// 2668: 숫자고르기
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

import static java.lang.System.exit;

public class B2668 {
    public static int N;
    public static int[][] maps;
    public static int[] idxList;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());

        idxList= new int[N];
        visited= new boolean[N];

        maps= new int[2][N];
        for(int i=0; i<N; i++) {
            maps[0][i]= i+1;
            maps[1][i]= Integer.parseInt(br.readLine());
            idxList[i]= i;
        }

        for(int i=N; i>=0; i--){
            dfs(visited, 0, i, i); // 집합의 개수 => N combinations r
        }
    }

    public static void dfs(boolean[] visited, int start, int r, int cnt) {
        if (r == 0) {
            if (is_same()) {
                System.out.println(cnt-1);
                print();
                exit(0);
            }
            return;
        } else {
            for(int i= start; i < N; i++) {
                visited[i]= true;
                dfs(visited, i + 1, r - 1, i);
                visited[i]= false;
            }
        }
    }

    // 두 집합이 같은지 확인하는 함수
    public static boolean is_same() {
        Set<Integer> set1= new HashSet<>();
        Set<Integer> set2= new HashSet<>();

        for (int i=0; i<N; i++) {
            if (visited[i]) {
                set1.add(maps[0][i]);
                set2.add(maps[1][i]);
            }
        }

        return set1.equals(set2);
    }

    // 원소 구성이 같은 두 집합의 인덱스들을 출력하는 함수
    public static void print() {
        for (int i=0; i<N; i++) {
            if (visited[i]) {
                System.out.println(i+1);
            }
        }
    }
}