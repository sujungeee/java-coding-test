package com.codingTestBook.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution04 {
    public static int[] solution04(int[] answers){
        int[][] person= {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] success= {0, 0, 0};
        int n= answers.length;

        for(int i=0; i<3; i++){
            for(int j=0; j< answers.length; j++) {
                // 수포자 패턴보다 정답 패턴의 길이가 더 길 경우를 위해
                if (answers[j] == person[i][j%person[i].length]) {
                    success[i]+=1;
                }
            }
        }

        int max= 0;
        for(int i: success){
            max= Math.max(i, max);
        }

        List<Integer> result= new ArrayList<Integer>();
        for(int i=0; i<3; i++){
            if(success[i]==max){
                result.add(i+1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String args[]){
        System.out.println(Arrays.toString(solution04(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(solution04(new int[]{1, 3, 2, 4, 2})));
    }
}
