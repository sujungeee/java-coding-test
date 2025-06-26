// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD&categoryId=AV15PTkqAPYCFAYD&categoryType=CODE&problemTitle=%EA%B3%B5%ED%86%B5%EC%A1%B0%EC%83%81&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
package com.swea.course.jun_2025;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1248: 공통조상
 * # summary
 * : 두 노드의 "공통 조상"과 공통 조상이 루트인 "서브 트리의 크기" 구하기
 *
 * # access
 * 1. 두 노드의 공통 조상을 찾는 법
 *  : 노드 1의 부모 노드들을 저장하고, 노드 2의 부모 노드를 타고 올라가면서
 *    노드 1의 부모 노드와 겹치는게 있으면 해당 노드가 부모 노드!
 *
 * 2. 공통 조상이 루트인 서브 트리의 크기를 구하는 법
 *  : 특정 노드가 자식 노드가 있다면 자식을 특정 노드로 하여 재귀 호출하기.
 *
 * # logic
 * 1. find(): 공통 조상 찾기(by parent)
 *     1-1. 노드 1부터 부모 노드들을 모두 저장
 *     1-2. 노드 2부터 부모 노드로 거슬러 올라가면서 노드 1의 부모 노드 집합에 포함되는지 확인
 *         , 존재한다면 해당 노드가 가장 가까운 조상 노드이므로 return
 * 2. calculate(): 공통 조상이 루트인 서브 트리의 크기 구하기(by info)
 *    2-1. 기본 서브 트리 개수는 1(root)
 *    2-2. dfs로 자식이 있으면 자식 호출 & 트리 개수 +1
 */

public class S1248 {
    static int[] parent; // 인덱스: 자식 노드 / 값 : 부모 노드
    static List<Integer>[] info; // 인덱스: 부모 노드 / 값: 자식 노드 []
    static int cnt; // 서브 트리 개수

    public static int find(int node1, int node2) {
        Set<Integer> ancestors = new HashSet<>();
        // 1-1
        while (node1 != 0) {
            ancestors.add(node1);
            node1 = parent[node1];
        }

        // 1-2
        while (node2 != 0) {
            if (ancestors.contains(node2)) {
                return node2;
            }
            node2 = parent[node2];
        }
        return -1;
    }

    public static void calculate(int root) {
        if (!info[root].isEmpty()) {
            for (int node : info[root]) {
                calculate(node);
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            parent = new int[V + 1];
            info = new ArrayList[V + 1];
            for (int j = 1; j <= V; j++) {
                info[j] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                info[p].add(c);
            }

            // 1
            int ancestor = find(node1, node2);

            // 2
            cnt = 1;
            calculate(ancestor);

            System.out.printf("#%d %d %d\n", i, ancestor, cnt);
        }
    }
}