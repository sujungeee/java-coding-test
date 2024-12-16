// https://www.acmicpc.net/problem/2608
// 2608: 로마 숫자
package com.baekjoon;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;

public class B2608 {

    static HashMap<String, Integer> rome_symbols= new HashMap<>();
    static {
        rome_symbols.put("I", 1);
        rome_symbols.put("V", 5);
        rome_symbols.put("X", 10);
        rome_symbols.put("L", 50);
        rome_symbols.put("C", 100);
        rome_symbols.put("D", 500);
        rome_symbols.put("M", 1000);
        rome_symbols.put("IV", 4);
        rome_symbols.put("IX", 9);
        rome_symbols.put("XL", 40);
        rome_symbols.put("XC", 90);
        rome_symbols.put("CD", 400);
        rome_symbols.put("CM", 900);
    }
    static String[] rome_order = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    static String n1;
    static String n2;
    static Integer arabic_num;
    static String rome_num;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        n1= br.readLine();
        n2= br.readLine();

        arabic_num= symbols_sum(n1, n2);
        rome_num= replace_to_rome(arabic_num);

        System.out.println(arabic_num);
        System.out.println(rome_num);
    }

    public static int symbols_sum(String n1, String n2){
        return replace_to_arabic(n1) + replace_to_arabic(n2);
    }

    public static Integer replace_to_arabic(String rome){
        int num= 0;
        for(int i=0; i<rome.length(); i++){
            if(i+1 < rome.length()){
                String tmp= rome.substring(i, i+2);
                if (rome_symbols.containsKey(tmp)){
                    num+= rome_symbols.get(tmp);
                    i++;
                } else{
                    num+= rome_symbols.get(String.valueOf(rome.charAt(i)));
                }
            } else{
                num+= rome_symbols.get(String.valueOf(rome.charAt(i)));
            }
        }
        return num;
    }

    public static String replace_to_rome(int arabic){
        StringBuilder sb= new StringBuilder();

        for(String rome: rome_order){
            while(arabic >= rome_symbols.get(rome)){
                sb.append(rome);
                arabic-= rome_symbols.get(rome);
            }
        }

        return sb.toString();
    }
}