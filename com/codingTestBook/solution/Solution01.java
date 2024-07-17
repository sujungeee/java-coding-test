package com.codingTestBook.solution;

import java.util.Arrays;

public class Solution01 {
    public static int[] solution01_1(int[] arr) { // 원본을 바꾸는 정렬
        Arrays.sort(arr);
        return arr;
    }

    public static int[] solution01_2(int[] arr){ // 원본 배열을 복사한 새 배열을 정렬
        int[] clone= arr.clone(); // 파이썬의 .copy()와 같은 기능
        Arrays.sort(clone);
        return clone;
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(solution01_1(new int[]{1, -5, 2, 4, 3})));
        System.out.println(Arrays.toString(solution01_1(new int[]{2, 1, 1, 3, 2, 5, 4})));
        System.out.println(Arrays.toString(solution01_1(new int[]{6, 1, 7})));
    }
}
