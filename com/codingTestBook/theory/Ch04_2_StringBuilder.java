package com.codingTestBook.theory;

public class Ch04_2_StringBuilder {
    public static void main(String[] args){
        StringBuilder sb= new StringBuilder();

        sb.append(10);
        sb.append("ABC");
        System.out.println(sb); // 10ABC

        // 문자 추가와 삭제
        sb.insert(1, 2);
        System.out.println(sb); // 120ABC

        sb.deleteCharAt(3);
        System.out.println(sb); // 120BC
    }
}
