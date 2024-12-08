package com.programmers;

import java.util.ArrayList;
import java.util.List;

public class P155652 {
    public static String solution155652(String s, String skip, int index){
        StringBuilder sb= new StringBuilder();
        List<Character> list= new ArrayList<>();
        for(int i=0; i<26; i++){
            list.add((char)('a'+i));
        }

        for(Character item: skip.toCharArray()){
            list.remove(item);
        }

        for(Character ch: s.toCharArray()){
            int idx= list.indexOf(ch);
            idx= (idx+index) % list.size();
            sb.append(list.get(idx));
        }

        return sb.toString();
    }

    public static void main(String args[]){
        System.out.println(solution155652("aukks", "wbqd", 5));
    }
}
