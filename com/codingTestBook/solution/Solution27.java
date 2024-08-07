package com.codingTestBook.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution27 {
    public static int[] solution27(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] result= new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            result[i]= 0;
        }

        Map<String, Integer> enrollMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            enrollMap.put(enroll[i], i);
        }

        int price;
        String worker;

        for(int i=0; i<seller.length; i++) {
            price= 100*amount[i];
            worker= seller[i];
            result[enrollMap.get(worker)]+= price;

            while(enrollMap.get(worker) != null) {
                int divPrice= (int)(Math.ceil(price*0.9)); // 분배 금액
                result[enrollMap.get(worker)]= result[enrollMap.get(worker)] - price + divPrice; // 자기가 가지는 금액
                String pWorker= referral[enrollMap.get(worker)]; // 부모 노드 "-"
                if(!pWorker.equals("-")){
                    result[enrollMap.get(pWorker)] += (price-divPrice); // 부모가 가지는 금액
                }
                worker= pWorker;
                price-= divPrice;
                if(price<1){ // 분배할 금액이 1원 미만이면 break
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(solution27(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
                , new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
                , new String[] {"young", "john", "tod", "emily", "mary"}
                , new int[] {12, 4, 2, 5, 10})));

        System.out.println(Arrays.toString(solution27(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
                , new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
                , new String[] {"sam", "emily", "jaimie", "edward"}
                , new int[] {2, 3, 5, 4})));
    }
}
