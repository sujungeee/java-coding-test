// https://www.acmicpc.net/problem/1197
package com.baekjoon.bj_2026.mar3;

import java.io.*;
import java.util.*;

public class B1197 {
    static int V, E;
    static PriorityQueue<int[]> info;
    static int[] rel;
    static long answer;

    static int find(int node) {
        if (rel[node] != node) {
            rel[node] = find(rel[node]);
        }
        return rel[node];
    }

    static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            rel[parentB] = parentA;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        info = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            info.add(new int[] {w, s, e});
        }
        rel = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            rel[i] = i;
        }

        int cnt = 0; // 간선 개수
        while (cnt < V - 1) {
            int[] ss = info.poll();

            if (union(ss[1], ss[2])) {
                answer += ss[0];
                cnt++;
            }
        }

        System.out.println(answer);
    }
}