// https://www.acmicpc.net/problem/10830
package com.baekjoon.oct_2025;

/**
 * 10830: 행렬 제곱
 * # summary
 * : A의 B제곱 결과를 출력하자.
 * # access
 * 1. B가 100,000,000,000 이하이므로, 다 계산할 수 없음!
 * => 분할정복 알고리즘을 이용하자.
 * # logic
 * 1. 행렬의 0승은 단위행렬(I)이므로, init 행렬을 초기화한다.
 *   (단위행렬: 대각 원소가 모두 1인 행렬)
 * 2. power(num): arr의 num승을 구하는 함수
 *     2-1. (종료 조건) num이 1이면 arr의 1승이므로 arr을, 0이면 arr의 0승이므로 init을 반환한다.
 *     2-2. half(arr의 num/2 승)를 분할정복으로 구한다.
 *     2-3. square(half의 2승)을 구한다.
 *     2-4. num이 짝수면 square이 곧 num승이 되므로 square를,
 *          num이 홀수면 square * arr이 num승이 되므로 square * arr를 반환한다.
 *  3. multiply(m1, m2): m1과 m2 행렬의 곱을 구하는 함수
 *  4. arr의 B승의 결과인 result를 행 단위로 출력한다.
 */

import java.io.*;
import java.util.*;

public class B10830 {
    static int[][] init;
    static int N;
    static Long B;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1
        init = new int[N][N];
        for (int i = 0; i < N; i++) init[i][i] = 1;

        // 2
        int[][] result = new int[N][N];
        result = power(B);

        // 4
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] % 1000);
                if (j != N - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // 3
    static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(result[i], 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += ((m1[i][k] % 1000) * (m2[k][j] % 1000)) % 1000;
                }
                result[i][j] = sum % 1000;
            }
        }
        return result;
    }

    // 2
    static int[][] power(long num) {
        // 2-1
        if (num == 1) {
            return arr;
        } else if (num == 0) {
            return init;
        }
        // 2-2
        int[][] half = power(num / 2);

        // 2-3
        int[][] square = multiply(half, half);
        // 2-4
        if (num % 2 == 0) {
            return square;
        } else {
            return multiply(square, arr);
        }
    }
}