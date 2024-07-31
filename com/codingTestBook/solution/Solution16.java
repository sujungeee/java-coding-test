package com.codingTestBook.solution;

import java.util.*;

public class Solution16 {
    // 배열 + 쌩구현
    public static int[] solution16(int[] progresses, int[] speeds){
        int[] days= new int[progresses.length];
        for(int i=0; i<progresses.length; i++) {
            days[i] = (99 - progresses[i]) / speeds[i] + 1;
        }

        int count= 1;
        int max= days[0];
        for(int i=0; i<days.length; i++){
            if(max < days[i]){
                count+=1;
                max= days[i];
            }
        }
        int[] result= new int[count];
        int idx= 0;
        max= 0;
        for(int i=0; i<days.length; i++){
            if(max < days[i]){
                max= days[i];
                result[idx]=i;
                idx+=1;
            }
        }

        for(int i=0; i<count-1; i++){
            result[i]= result[i+1]-result[i];
        }
        result[count-1]= days.length - result[count-1];

        return result;
    }

    // 스택 이용
    public static int[] solution16_2(int[] progresses, int[] speeds){
        Stack<Integer> answer= new Stack<>();
        int n= progresses.length;
        // 각 기능의 남은 일수 계산
        int[] days= new int[n];
        for(int i=0; i<n; i++){
            days[i]= (int)Math.ceil((100.0-progresses[i]) / speeds[i]);
        }

        int count= 0;
        int max= days[0];

        for(int i=0; i<n; i++){
            if(days[i] > max){
                answer.add(count);
                count= 1;
                max= days[i];
            } else{
                count++;
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 큐 이용: 베스트 풀이
    public static int[] solution16_3(int[] progresses, int[] speeds){
        Queue<Integer> q= new ArrayDeque<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);

            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }

            q.offer(date);
        }

        answerList.add(q.size());
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String args[]){
        System.out.println(Arrays.toString(solution16_3(
                new int[] {93, 30, 55},
                new int[] {1, 30, 5}
        )));

        System.out.println(Arrays.toString(solution16_3(
                new int[] {95, 90, 99, 99, 80, 99},
                new int[] {1, 1, 1, 1, 1, 1}
        )));
    }
}
