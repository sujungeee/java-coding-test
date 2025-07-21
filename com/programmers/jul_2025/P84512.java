// https://school.programmers.co.kr/learn/courses/30/lessons/84512
package com.programmers.jul_2025;

/**
 * 84512: 모음사전
 * # summary
 * : 단어가 주어질 때, 사전 상 몇 번째 단어인지 구하기
 * # access
 * : 알파벳 순서대로 완전 탐색을 하며 순서(answer)를 늘리다가 word를 만나면 그 순서를 return하기! (재귀 이용)
 * # logic
 * 1. 알파벳을 추가하여 함수를 호출하며 다음의 조건 중 원하는 조건이 있는지 확인하기
 *     1-1. 알파벳을 덧붙인 result라는 문자열이 주어진 단어(word)와 동일한지 확인한다.
 *     1-2. 알파벳을 덧붙인 result라는 문자열이 단어의 최대 길이(5)를 넘는지 확인한다.
 *   이 중, 1-1에 속하면 원하는 조건이기 때문에 true를 return하고 더 이상의 함수 호출을 중단한다.
 * 2. 순서 상으로 다음 알파벳을 단어 끝에 덧붙여 새로 함수를 호출한다.
 */


class Solution84512 {
    static String[] alphabets = {"A", "E", "I", "O", "U"};
    static int answer;

    public int solution(String word) {
        answer = 0;
        StringBuilder sb = new StringBuilder();
        search(sb, word);
        return answer;
    }

    public boolean search(StringBuilder result, String word) {
        // 1
        if (result.toString().equals(word)) return true;
        if (result.length() >= 5) return false;

        // 2
        for (String alphabet : alphabets) {
            answer++;
            result.append(alphabet);
            if (search(result, word)) return true;
            result.deleteCharAt(result.length() - 1);
        }

        return false;
    }
}

public class P84512 {
    public static void main(String[] args) {
        Solution84512 s = new Solution84512();

        System.out.println(s.solution("AAAAE"));
        System.out.println(s.solution("AAAE"));
        System.out.println(s.solution("I"));
        System.out.println(s.solution("EIO"));
    }
}