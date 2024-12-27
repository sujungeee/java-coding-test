// https://www.acmicpc.net/problem/17349
// 17349: 1루수가 누구야
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B17349 {
    public static int[][] arrs;
    public static Set<Integer> is_1;
    public static Set<Integer> is_not_1;
    public static Set<Integer> answers;
    public static Set<Integer> filteredSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        arrs= new int[9][2];
        for(int i=0; i<9; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            arrs[i][0]= Integer.parseInt(st.nextToken());
            arrs[i][1]= Integer.parseInt(st.nextToken());
        }

        answers= new HashSet<>();
        for (int i=0; i<9; i++) {
            is_liar(i);
        }

        filteredSet = new HashSet<>(answers);
        filteredSet.remove(-1);

        if (filteredSet.size() == 0) {
            System.out.println(-1);
        } else if (filteredSet.size() == 1) {
            System.out.println(filteredSet.iterator().next());
        } else {
            System.out.println(-1);
        }
    }

    private static void is_liar(int idx) {
        is_1= new HashSet<>();
        is_not_1= new HashSet<>();

        for(int i=0; i<9; i++) {
            if (i != idx){
                if (arrs[i][0] == 1) {
                    is_1.add(arrs[i][1]);
                } else {
                    is_not_1.add(arrs[i][1]);
                }
            } else { // 라이어인 경우
                if (arrs[i][0] == 1) {
                    is_not_1.add(arrs[i][1]);
                } else {
                    is_1.add(arrs[i][1]);
                }
            }
        }

        // 1. is에 하나의 숫자만 있고, is_not에 그 숫자가 없는 경우
        if (is_1.size() == 1) {
            int unique = is_1.iterator().next();
            if (!is_not_1.contains(unique)) {
                answers.add(unique);
                return;
            }
        }

        // 2. is에 아무것도 없고, is_not에 특정 숫자 하나를 제외한 나머지 8개의 숫자가 모두 있는 경우
        if (is_1.isEmpty()) {
            for (int i = 1; i <= 9; i++) {
                if (!is_not_1.contains(i)) {
                    answers.add(i);
                }
            }
            return;
        }

        answers.add(-1);
    }
}