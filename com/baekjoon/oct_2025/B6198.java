package com.baekjoon.oct_2025;

/**
 * 6198: 옥상 정원 꾸미기
 * # summary
 * : 빌딩이 바라볼 수 있는 다른 빌딩의 개수의 총 합을 구하기
 * # access
 * : 스택 안에 있는 높은 빌딩이 현재(buildings[i])를 볼 수 있음!
 */

import java.io.*;
import java.util.*;

public class B6198 {
    static int N;
    static int[] buildings;

    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int h = buildings[i];
            while (!stack.empty() && stack.peek() <= h) {
                stack.pop();
            }
            answer += stack.size();
            stack.push(h);
        }

        System.out.println(answer);
    }
}