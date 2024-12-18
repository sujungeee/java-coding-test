package com.theory.dataStructure;

import java.util.Collections;
import java.util.PriorityQueue;

public class DataStructure_PriorityQueue {

    public static class Subject implements Comparable<Subject> {
        int eng;
        int math;

        Subject(int eng, int math) {
            this.eng= eng;
            this.math= math;
        }
        public int compareTo(Subject o) {
            if (this.eng == o.eng) {
                return o.math - this.math; // 2. 높은 수학 점수 우선
            } else {
                return this.eng - o.eng; // 1. 낮은 영어 점수 우선
            }
        }
    }

    public static void main(String[] args) {
        // 최소힙
        PriorityQueue<Integer> minHeapq= new PriorityQueue<>();

        // 최대힙
        PriorityQueue<Integer> maxHeapq= new PriorityQueue<>(Collections.reverseOrder());

        // 기본 메소드(add, remove: 큐가 꽉 차거나 비어있으면 에러 발생)
        minHeapq.add(2);
        minHeapq.add(8);
        minHeapq.add(6);

        // + 우선 순위 큐 초기화: maxHeapq.clear()
        // 우선 순위 큐에 있는 원소의 수: maxHeapq.size()

        while (!minHeapq.isEmpty()) {
            System.out.printf("minHeapq remove: %d\n", minHeapq.remove());
        }

        // offer, poll: 큐가 꽉차거나 비어있으면 false, null 반환
        System.out.println("비어있는 maxHeapq poll: " + maxHeapq.poll());

        maxHeapq.offer(3);
        maxHeapq.offer(5);
        maxHeapq.offer(6);

        while (!maxHeapq.isEmpty()) {
            System.out.printf("maxHeapq poll: %d\n", maxHeapq.poll());
        }

        // 우선 순위를 remove하는 기준
        // Subject
        PriorityQueue<Subject> sHeapq= new PriorityQueue<>();
        sHeapq.add(new Subject(30, 50));
        sHeapq.add(new Subject(50, 30));
        sHeapq.add(new Subject(50, 70));

        while (!sHeapq.isEmpty()) {
            Subject sub= sHeapq.remove();
            System.out.printf("sHeapq remove- 영어: %d, 수학: %d\n", sub.eng, sub.math);
        }
    }
}
