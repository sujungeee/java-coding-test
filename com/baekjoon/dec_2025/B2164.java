// 2164: 카드 2
// https://www.acmicpc.net/problem/2164
package com.baekjoon.dec_2025;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B2164 {
    static int N;

    public static void main(String[] args) throws IOException {
        int answer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            queue.addLast(i);
        }

        while (true) {
            int discard = queue.pollFirst();
            if (queue.isEmpty()) {
                answer = discard;
                break;
            } else {
                int last = queue.pollFirst();
                queue.addLast(last);
            }
        }

        System.out.println(answer);
    }
}