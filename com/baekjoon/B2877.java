// https://www.acmicpc.net/problem/2877
// 2877: 4와 7
package com.baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2877 {
    public static int N;
    public static int digit;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());

        digit= 1; // 자릿수

        if (N == 1) {
            System.out.println(4);
        } else if (N == 2) {
            System.out.println(7);
        } else {
            // K번째 작은 수의 자릿수 구하기(digit)
            int beforeCnt= 0;
            while(beforeCnt < N) {
                beforeCnt+= 1 << digit;
                digit++;
            }
            digit--;
            beforeCnt-= 1 << digit;

            // digit 자릿수를 가진 N - beforeCnt 번째 숫자를 출력
            for(int i = 0; i < N - beforeCnt; i++) {

            }
            System.out.println(answer);
        }
    }
}