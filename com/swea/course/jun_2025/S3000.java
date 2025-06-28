// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV-fO0s6ARoDFAXT&categoryId=AV-fO0s6ARoDFAXT&categoryType=CODE&problemTitle=%EC%A4%91%EA%B0%84%EA%B0%92&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
package com.swea.course.jun_2025;

/**
 * 3000: 중간값 구하기
 *
 * # summary
 * : 중간값을 구하자.
 * # access
 * 1. 매 N번 마다 정렬을 하고, 중간값을 구하면 O(N^2)
 *    * N은 최대 200,000이다. 즉 시간 초과다..
 * 2. 두 힙(최소힙, 최대힙)을 이용한다.
 *    => 중간값을 포함한 큰 값들은 최소힙에 넣고 중간값보다 작은 값들을 최대힙에 넣는다.
 *    => 최소힙에서 값을 하나 꺼내면 그 값이 바로 중간값!
 * # logic
 * 1. 처음 입력받은 값을 최소힙에 넣는다.
 * 2. 그 다음부터 들어오는 두 값이 중간값보다 큰지 작은지 여부에 따라 다음과 같이 나뉜다.
 *    2-1. 두 값이 중간값보다 모두 작을 경우
 *        -> 입력 받은 두 값을 최대 힙에 넣는다.
 *        -> 최대 힙에서 가장 큰 값을 새로운 중간값으로 바꾸고, 그 값을 최소힙에 넣는다.
 *    2-2. 두 값이 중간값보다 모두 클 경우
 *        -> 입력 받은 두 값을 최소 힙에 넣는다.
 *        -> 최소 힙에서 가장 작은 값을 최대힙으로 넘기고, 최소힙에서 가장 작은 값을 새로운 중간값으로 바꾼다.
 *    2-3. 한 값은 중간값보다 크고, 한 값은 중간값보다 작을 경우: 작은 값은 최대힙, 큰 값은 최소힙에 넣고 중간값은 기존 중간값 그대로
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3000 {
    static PriorityQueue<Integer> minQueue;
    static PriorityQueue<Integer> maxQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= N; testCase++) {
            long answer = 0;

            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>(Collections.reverseOrder());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int medium = Integer.parseInt(st.nextToken());
            minQueue.add(medium);

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                if (num1 < medium && num2 < medium) {
                    // 2-1
                    maxQueue.add(num1);
                    maxQueue.add(num2);
                    medium = maxQueue.poll();
                    minQueue.add(medium);
                } else if (num1 > medium && num2 > medium) {
                    // 2-2
                    minQueue.add(num1);
                    minQueue.add(num2);
                    maxQueue.add(minQueue.poll());
                    medium = minQueue.peek();
                } else if ((num1 > medium && num2 < medium) || (num1 < medium && num2 > medium)) {
                    // 2-3
                    int min = Math.min(num1, num2);
                    int max = Math.max(num1, num2);
                    maxQueue.add(min);
                    minQueue.add(max);
                }

                answer += medium;
                answer %= 20171109;
            }

            System.out.printf("#%d %d\n", testCase, answer);
        }
    }
}