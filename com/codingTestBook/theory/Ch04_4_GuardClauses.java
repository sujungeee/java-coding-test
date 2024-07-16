package com.codingTestBook.theory;

import java.util.ArrayList;
import java.util.List;

public class Ch04_4_GuardClauses {
    public static void main(String[] args){
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(calculateAverage(list)); // 2.5
    }

    static double calculateAverage(List<Integer> numbers){
        if(numbers == null){
            return 0; // null이면 종료(예외)
        }
        if(numbers.isEmpty()){
            return 0; // 데이터가 없으면 종료(예외)
        }

        int total= numbers.stream().mapToInt(i -> i).sum();
        return (double)total/numbers.size();
    }
}
