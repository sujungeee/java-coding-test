package com.codingTestBook.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution19 {
    // sol1: 차집합 이용하면 될듯: 집합 부분 배우고 다시 고려해보기
    // sol2: completion 배열을 돌면서 participants를 제거하고 남는게 정답
    // ArrayList는 굉장히 시간복잡도가 높은듯.. 웬만하면 지양..
    public static String solution19_2(String[] participant, String[] completion){
        List<String> pcArr= new ArrayList<>(Arrays.asList(participant));
        for(int i=0; i<completion.length; i++){
            pcArr.remove(completion[i]);
        }
        return pcArr.get(0);
    }

    // sol3: map을 이용해서 두 맵을 각각 이름:개수로 만든 후, participant에서 새로 생겨난 키 or 값이 하나 더 많은 키가 정답
    public static String solution19_3(String[] participant, String[] completion){
        HashMap<String, Integer> map1= new HashMap<>();
        HashMap<String, Integer> map2= new HashMap<>();
        for(int i=0; i<participant.length; i++){
            if(map1.containsKey(participant[i])){
                map1.put(participant[i], map1.get(participant[i])+1);
            } else{
                map1.put(participant[i], 1);
            }
        }
        for(int i=0; i<completion.length; i++){
            if(map2.containsKey(completion[i])){
                map2.put(completion[i], map2.get(completion[i])+1);
            } else{
                map2.put(completion[i], 1);
            }
        }

        // completion을 돌면서 없는 키 or 값이 1 많은 키 탐색
        for(int i=0; i< participant.length; i++){
            if(map2.containsKey(participant[i])==false){
                return participant[i];
            } else if(map1.get(participant[i]) > map2.get(participant[i])){
                return participant[i];
            }
        }
        return "";
    }

    // sol4: sol3 개선(교재 참고)
    public static String solution19_4(String[] participant, String[] completion){
        HashMap<String, Integer> map= new HashMap<>();
        for(String com: completion){
            map.put(com, map.getOrDefault(com, 0)+1);
        }

        for(String pc: participant){
            if(map.getOrDefault(pc, 0) == 0){
                return pc;
            }
            map.put(pc, map.get(pc)-1);
        }

        return "";
    }

    public static void main(String args[]){
        System.out.println(solution19_4(
                new String[] {"leo", "kiki", "eden"},
                new String[] {"eden", "kiki"}
        ));
        System.out.println(solution19_4(
                new String[] {"marina", "josipa", "nikola", "vinko", "filipa"},
                new String[] {"josipa", "filipa", "marina", "nikola"}
        ));
        System.out.println(solution19_4(
                new String[] {"mislav", "stanko", "mislav", "ana"},
                new String[] {"stanko", "ana", "mislav"}
        ));
    }
}
