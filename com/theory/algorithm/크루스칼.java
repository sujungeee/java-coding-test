package com.theory.algorithm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준(13905)
public class 크루스칼 {
    static int N, M;
    static int start, end;
    static int[] parent;
    static PriorityQueue<Info> pq;

    static class Info implements Comparable<Info>{
        int u, v, cost;

        Info(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        public int compareTo(Info other) {
            return Integer.compare(other.cost, this.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 1
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 2
        pq = new PriorityQueue<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Info(u, v, cost));
        }

        // 3
        while(!pq.isEmpty()) {
            Info info = pq.poll();
            union(info.u, info.v);

            if (find(start) == find(end)) {
                answer = info.cost;
                break;
            }
        }

        System.out.println(answer);
    }

    // 4
    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        parent[parentB] = parentA;
    }

    // 5
    static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }
}