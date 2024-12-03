package com.codingTestBook.solution;

import java.util.*;

public class Solution06 {
    public static int[] solution06(int N, int[] stages){
        List<Integer> result= new ArrayList<>();

        Map<Integer, Integer> hashMap= new HashMap<Integer, Integer>();
        for(int i=0; i<stages.length;  i++){
            hashMap.put(stages[i], hashMap.getOrDefault(stages[i], 0) + 1);
        }

        // 실패율 저장하는 해시맵
        int total= stages.length;
        Map<Integer, Float> failsMap= new HashMap<Integer, Float>();
        for(int i=0; i<N; i++){
            // 스테이지 별 도전하는 사람 수
            if(total!=0){
                int num= hashMap.getOrDefault(i+1, 0);
                failsMap.put(i+1, (float)num/total);
                total-=num;
            } else { // 특정 스테이지 이상을 도전하는 사람이 없으면
                failsMap.put(i+1, (float)0);
            }
        }

        // 맵에서의 정렬(값을 내림차순으로 인덱스 정렬, 값이 같으면 인덱스를 오름차순으로 정렬)
        List<Map.Entry<Integer, Float>> entryList = new LinkedList<>(failsMap.entrySet());
        entryList.sort((o1, o2) -> {
            int valueCompare = Float.compare(o2.getValue(), o1.getValue()); // 내림차순 정렬
            if (valueCompare == 0) {
                return Integer.compare(o1.getKey(), o2.getKey()); // 값이 같으면 키 기준 오름차순 정렬
            }
            return valueCompare;
        });

        for(Map.Entry<Integer, Float> es: entryList){
            result.add(es.getKey());
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(solution06(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(solution06(4, new int[] {4, 4, 4, 4, 4})));
    }
}
