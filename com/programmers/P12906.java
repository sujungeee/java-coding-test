// https://school.programmers.co.kr/learn/courses/30/lessons/12906
package com.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P12906 {

    public static int[] solution12906(int[] arr){
        List<Integer> result= new ArrayList<>();
        result.add(arr[0]);
        for(int i= 1; i < arr.length; i++){
            if(arr[i] == arr[i-1]){
                continue;
            } else{
                result.add(arr[i]);
            }
        }
        // 스트림 사용
        int[] answer= result.stream().mapToInt(Integer::intValue).toArray();

        // List<Integer>를 int[]로 변환
//        int[] answer = new int[result.size()];
//        for (int i = 0; i < result.size(); i++) {
//            answer[i] = result.get(i);
//        }

        return answer;
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(solution12906(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(solution12906(new int[]{4, 4, 4, 3, 3})));
    }
}
