package com.codingTestBook.solution;

import java.util.HashMap;

public class Solution20 {
    public static int solution20(String[] want, int[] number, String[] discount){
        int result= 0;
        HashMap<String, Integer> map= new HashMap<>();
        int n= want.length;
        for(int i=0; i<n; i++){
            map.put(want[i], number[i]);
        }

        for(int i=0; i< discount.length-9; i++){
            HashMap<String, Integer> tmp= new HashMap<>();
            for(int j=i; j<i+10; j++){
                if(tmp.containsKey(discount[j])){
                    tmp.put(discount[j], tmp.get(discount[j]) + 1);
                } else{
                    tmp.put(discount[j], 1);
                }
            }
            if(tmp.equals(map)){
                result+=1;
            }
        }
        return result;
    }

    public static void main(String args[]){
        System.out.println(solution20(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}
        ));

        System.out.println(solution20(
                new String[] {"apple"},
                new int[] {10},
                new String[] {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        ));
    }
}
