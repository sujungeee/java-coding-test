package com.programmers;

import java.util.ArrayDeque;

public class P17680 {
    public static int solution17680(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) return cities.length*5;

        ArrayDeque<String> que= new ArrayDeque<>();
        for (String city: cities){
            String str= city.toLowerCase();

            if (que.contains(str)){
                answer++;
                que.remove(str);
            } else {
                if (que.size() >= cacheSize){
                    que.remove();
                }
                answer+= 5;
            }
            que.add(str);
        }

        return answer;
    }

    public static void main(String[] args){
        System.out.println(solution17680(3, new String[]{
                "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"
        }));

        System.out.println(solution17680(0, new String[]{
                "Jeju", "Pangyo", "Seoul", "NewYork", "LA"
        }));
    }
}

