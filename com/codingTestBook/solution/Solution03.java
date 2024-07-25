package com.codingTestBook.solution;

import java.util.Arrays;
import java.util.HashSet;

public class Solution03 {
    public static int[] solution03(int[] numbers){
        HashSet<Integer> result= new HashSet<>();

        for(int i= 0; i< numbers.length -1; i++){
            for(int j=i+1; j < numbers.length-1; j++){
                result.add(numbers[i]+numbers[j]);
            }
        }
        return result.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution03(new int[]{2, 1, 3, 4, 1})));
        System.out.println(Arrays.toString(solution03(new int[]{5, 0, 2, 7})));
    }
}
