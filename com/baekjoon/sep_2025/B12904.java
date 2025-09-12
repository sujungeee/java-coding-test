package com.baekjoon.sep_2025;

/**
 * 12904: A와 B
 * # summary
 * : 두 규칙으로, S에서 T를 만들 수 있는지 구하기
 * # access
 * 1. 완전 탐색 불가.. 최대 2^1000
 * 2. 거꾸로 생각하자! (= T를 S로 만들자)
 *    만약, 맨 뒤 문자열이 A라면 1번 규칙을 / B라면 2번 규칙을 적용한 것이기 때문에
 *    -> 문자열을 제거하고, 2번 규칙에 한해 문자열을 뒤집어야 함.
 * # logic
 * 1. 두 문자열을 받아, 각각 char 리스트로 변환한다.
 * 2. 규칙을 반대로 적용한다.
 *    2-1. 두 규칙 모두 마지막에 문자열을 추가해야 하므로, 맨 끝 문자열을 제거한다.
 *    2-2. 제거한 문자열이 'B'라면 문자열을 뒤집는다.
 * 3. T 배열을 S 배열의 사이즈로 축소를 마치면, 두 배열의 동등 비교를 진행한다.
 *    같으면 역으로 S를 T로 만들 수 있다는 의미이므로 1을 출력한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B12904 {
    static List<Character> SArray, TArray;
    static int sLength, tLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1
        String S = br.readLine();
        SArray = new ArrayList<>();
        for (char c : S.toCharArray()) SArray.add(c);
        sLength = S.length();

        String T = br.readLine();
        TArray = new ArrayList<>();
        for (char c : T.toCharArray()) TArray.add(c);
        tLength = T.length();

        int idx = tLength;
        // 2
        while (idx > sLength) {
            // 2-1
            char c = TArray.remove(--idx);
            // 2-2
            if (c == 'B') Collections.reverse(TArray);
        }

        // 3
        if (SArray.equals(TArray)) System.out.println(1);
        else System.out.println(0);
    }
}