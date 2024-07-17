package com.codingTestBook.solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Solution02 {
    public static int[] solution02_1(int[] arr){ // by 스트림
        Integer[] result= Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new);
        Arrays.sort(result, Collections.reverseOrder());

        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution02_2(int[] arr){ // by TreeSet
        TreeSet<Integer> set= new TreeSet<>(Collections.reverseOrder());
        for(int num: arr){
            set.add(num);
        }
        // int 형 배열에 담아서 반환
        int[] result= new int[set.size()];
        for(int i=0; i<result.length; i++){
            result[i]= set.pollFirst();
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(solution02_1(new int[]{4, 2, 2, 1, 3, 4})));
        System.out.println(Arrays.toString(solution02_2(new int[]{2, 1, 1, 3, 2, 5, 4})));
    }
}
