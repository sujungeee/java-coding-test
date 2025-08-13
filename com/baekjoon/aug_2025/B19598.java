package com.baekjoon.aug_2025;

/**
 * 19598: 최소 회의실 개수
 * # summary
 * : 회의를 모두 끝낼 수 있는 회의실의 최소 개수를 구하자.
 * # access
 * : 희의가 끝나는 시간에 가장 가까이 시작할 수 있는 회의를 선택하자!
 * => 그리디 알고리즘
 * # logic
 * 1. 시작 시간이 빠른 순으로 회의를 진행해야 회의실 개수를 최소로 쓸 수 있으므로, 우선순위 큐(times)로 회의들의 시간을 모두 넣는다.
 * 2. 시작 시간이 가장 빠른 회의를 꺼내어,
 *    방(rooms)이 비어있다면 -> 회의의 끝나는 시간을 새로운 우선순위 큐(rooms)에 넣는다.
 * 3. 방이 비어있지 않으면 -> 현재 방에서 가장 빨리 끝나는 회의 시간을 우선순위 큐(rooms)에서 꺼낸다.
 *    3-1. 그 시간이 현재 회의 시작 시간보다 뒤에 있으면 들어갈 수 있는 회의실이 없기 때문에 꺼낸 "회의 끝내는 시간"의 정보를 다시 넣는다.
 *    3-2. 현재 회의의 끝나는 시간 또한 우선순위 큐(rooms)에 넣는다.
 *         (현재 회의 시작 시간보다 가장 빨리 끝나는 회의 시간이 뒤에 있어도 새로 회의를 넣어야 하고,
 *         현재 회의 시작 시간보다 가장 빨리 끝나는 회의 시간이 앞에 있어도 현재 회의로 교체해야 하기 때문이다.)
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class B19598 {
    static int N;
    static PriorityQueue<Integer> rooms;
    static PriorityQueue<int[]> times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rooms = new PriorityQueue<>();

        // 1
        times = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times.add(new int[] {start, end});
        }

        while(!times.isEmpty()) {
            // 2
            int[] time = times.poll();
            int start = time[0];
            int end = time[1];

            if (rooms.isEmpty()) {
                rooms.add(end);
            } else {
                // 3
                int minEnd = rooms.poll();
                // 3-1
                if (start < minEnd) {
                    rooms.add(minEnd);
                }
                // 3-2
                rooms.add(end);
            }
        }

        System.out.println(rooms.size());
    }
}