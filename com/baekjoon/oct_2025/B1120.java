// https://www.acmicpc.net/problem/1120
package com.baekjoon.oct_2025;

import java.io.*;
import java.util.*;

public class B1120 {
    public static int answer;
    public static String a, b;
    public static int aLength, bLength;

    public static void main(String[] args) throws IOException {
        answer = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken();
        b = st.nextToken();
        aLength = a.length();
        bLength = b.length();

        for(int front = 0; front <= bLength - aLength; front++) {
            int back = bLength - aLength - front;
            int cnt = 0;
            for (int i = front; i < bLength - back; i++) {
                if (a.charAt(i - front) != b.charAt(i)) cnt++;
            }
            answer = Math.min(answer, cnt);
        }

        System.out.println(answer);
    }
}