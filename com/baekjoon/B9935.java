// https://www.acmicpc.net/problem/9935
// 9935: 문자열 폭발
package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class B9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String exp_str = new StringBuilder(br.readLine()).reverse().toString();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        int n1 = str.length();
        int n2 = exp_str.length();
        for (int i = 0; i < n1; i++) {
            stack.addLast(str.charAt(i));
            if (stack.size() >= n2) {
                int idx= -1;
                for (int j=0; j < n2; j++){
                    char c= stack.peekLast();

                    // 폭발 문자열과 같지 않다면
                    if (c != exp_str.charAt(j)){
                        idx= j;
                        break;
                    }

                    stack.pollLast();
                }

                if (idx != -1){
                    for(int j=idx-1; j>=0; j--){
                        stack.addLast(exp_str.charAt(j));
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (char s : stack) {
                sb.append(s);
            }
            System.out.println(sb);
        } else {
            System.out.println("FRULA");
        }

    }
}