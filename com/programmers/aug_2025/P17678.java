// https://school.programmers.co.kr/learn/courses/30/lessons/17678
package com.programmers.aug_2025;

/**
 * 17678: [1차] 셔틀버스
 * # summary
 * : 9시부터 n번, 간격 t, 최대 크루 수 m
 *  + 갈 수 있는 제일 늦은 도착 시간
 *  + 같은 시간이면 뒤에 서야 함.
 *  + 셔틀 시간 이하는 출발 가능!
 * # access
 * 1. 셔틀을 운행하면서 보낼 사람 보내 버리기(by 우선순위 큐)
 * 2. 마지막 셔틀 시간에서 가장 뒤에 서도록 answer 출력하기
 * # logic
 * 1. 각 셔틀 출발 시간(departTime)보다 빨리 대기열에 서 있는 크루의 수(cnt)를 집계한다.
 *    (해당 크루는 대기열에서 제외한다.)
 * 2. 마지막 셔틀 시간에서 서 있는 크루의 수가 수용 가능한 최대 크루의 수(m)이면,
 *    마지막으로 대기열에 선 크루의 시간보다 1분 빨리 대기열 줄에 서있도록 answer를 갱신한다.
 * 3. 마지막 셔틀 시간에 대기열에 서 있는 크루의 수가 수용 가능한 최대 크루의 수(m) 미만이면,
 *    마지막 셔틀 출발 시간에 맞춰 줄에 서게 되므로 마지막 셔틀 출발 시간으로 answer를 갱신한다.
 */

import java.util.PriorityQueue;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Solution17678 {
    PriorityQueue<String> queue;

    public String solution(int n, int t, int m, String[] timetable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String answer = "";

        queue = new PriorityQueue<>();
        for (String time : timetable) {
            queue.add(time);
        }

        LocalTime departTime = LocalTime.of(8, 60 - t);
        for (int i = 1; i <= n; i++) {
            departTime = departTime.plusMinutes(t);
            int cnt = 0;
            while (!queue.isEmpty()) {
                String time = queue.peek();
                if ((LocalTime.parse(time).isBefore(departTime) || LocalTime.parse(time).equals(departTime)) && cnt < m) {
                    // 1
                    queue.poll();
                    cnt++;
                    // 2
                    if (i == n && cnt == m) {
                        answer = LocalTime.parse(time).minusMinutes(1).format(formatter);
                    }
                } else {
                    break;
                }
            }
        }

        // 3
        if (answer.isEmpty()) answer = departTime.format(formatter);
        return answer;
    }
}

public class P17678 {
    public static void main(String[] args)  {
        Solution17678 s = new Solution17678();

        System.out.println(s.solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"}));
    }
}
