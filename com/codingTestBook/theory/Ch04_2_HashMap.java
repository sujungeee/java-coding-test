package com.codingTestBook.theory;

import java.util.HashMap;

public class Ch04_2_HashMap {
    public static void main(String[] args){
        HashMap<String, Integer> map= new HashMap<>();

        // 해시 맵 데이터 삽입
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        System.out.println(map);

        // 해시 맵 데이터 검색
        String key= "apple";

        if(map.containsKey(key)){
            int value= map.get(key);
            System.out.println(key + ": " + value);
        } else{
            System.out.println(key + "는 해시 맵에 없습니다.");
        }

        // 해시 맵 수정
        map.put("banana", 4);
        System.out.println(map);

        // 해시 맵 삭제
        map.remove("orange");
        System.out.println(map);
    }
}
