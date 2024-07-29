package com.codingTestBook.solution;

//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Stack;
//import java.util.List;

public class Solution08{
//    public static boolean solution08(String str){
//        Stack<String> stack= new Stack<>();
//        List<String> strArr= new ArrayList<>(Arrays.asList(str.split("")));
////        char[] charArray= str.toCharArray();
//
//        for(String e: strArr){
//            if(stack.isEmpty() && e.equals(")")){
//                return false;
//            }
//            if(e.equals("(")) {
//                stack.push(e);
//            } else{
//                if(stack.peek().equals("(")){
//                    stack.pop();
//                }
//            }
//        }
//        if(stack.isEmpty()){
//            return true;
//        }
//        return false;
//    }

    // 효율성 통과: List보다 그냥 배열이 더 좋은듯
    public static boolean solution08_2(String str){
        Stack<Character> stack= new Stack<>();
        char[] charArray= str.toCharArray();

        for(Character e: charArray){
            if(stack.isEmpty() && e==')'){
                return false;
            }
            if(e=='(') {
                stack.push(e);
            } else{
                if(stack.peek()=='('){
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    // 다른 풀이
    public static boolean solution08_3(String s){
        int count= 0;
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i) == '('){
                count++;
            }
            if(s.charAt(i) == ')'){
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        if(count == 0){
            return true;
        }

        return false;
    }

    public static void main(String args[]){
        System.out.println(solution08_3("()()"));
        System.out.println(solution08_3("(())()"));
        System.out.println(solution08_3(")()("));
        System.out.println(solution08_3("(()("));
    }
}