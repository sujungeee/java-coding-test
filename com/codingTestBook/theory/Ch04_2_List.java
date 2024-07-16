package com.codingTestBook.theory;

import java.util.ArrayList;

public class Ch04_2_List {
    public static void main(String[] args){
        ArrayList<Integer> list= new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.get(2)); // 3
        System.out.println(list); // [1, 2, 3, 4]
    }
}
