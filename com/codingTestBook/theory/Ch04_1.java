package com.codingTestBook.theory;

public class Ch04_1 {
    public static void main(String[] args){
        double a= 0.1 + 0.2;
        double b= 0.3;

//        if(a==b){
//            System.out.println("a와 b는 같은 값입니다.");
//        } else{
//            System.out.println("a와 b는 다른 값입니다.");
//        }

        System.out.println(a-b); // 5.551115123125783E-17
        double epsilon= 5.551115123125783E-10; // 17보다 약간 작은 값이 좋다고 하는데 10이 좋다고도 함(chatgpt)
        if(Math.abs(a-b) < epsilon){
            System.out.println("a와 b는 같은 값입니다.");
        } else{
            System.out.println("a와 b는 다른 값입니다.");
        }
    }
}
