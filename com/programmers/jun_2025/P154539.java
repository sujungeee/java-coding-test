// https://school.programmers.co.kr/learn/courses/30/lessons/154539
package com.programmers.jun_2025;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 154539: 뒤에 있는 큰 수 찾기
 * # access
 * 1. O(N^2)는 무리. 왜냐면 N은 최대 100만
 * 2. 정렬 문제라고 생각했으나 스택..... (서칭의 도움을 받음..)
 *    이유: 가장 가까운 큰 숫자가 나올 때까지 기존의 작은 숫자 값을 알고 있어야 하므로 스택에 저장해야 한다.
 * # logic
 * 1. stack 구성: [인덱스, 값]
 * 2. stack이 비어있다면 비교할 값을 push 한다.
 * 3. stack이 비어있지 않다면 가장 맨 위 값을 확인해서 새 값(numbers[i])과 숫자를 비교해야 한다.
 *   새 값이 더 크면 작은 값들을 새 값으로 바꿔주어야 한다.
 *   따라서, 새로운 값보다 작은 값들을 계속 stack에서 꺼내서 해당 인덱스에 새 값을 할당.
 * 4. 비교가 다 끝나면 새 값을 push 한다.
 */

class Solution154539 {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < numbers.length; i++) {
            if (!stack.isEmpty()) {
                int topNum = stack.peekLast()[1];

                if (numbers[i] > topNum) {
                    while (!stack.isEmpty() && stack.peekLast()[1] < numbers[i]) {
                        int[] pop = stack.pollLast();
                        answer[pop[0]] = numbers[i];
                    }
                }
            }
            stack.addLast(new int[] {i, numbers[i]});
        }
        return answer;
    }
}

public class P154539 {
    public static void main(String[] args) {
        Solution154539 s = new Solution154539();

        System.out.println(Arrays.toString(s.solution(new int[]{2, 3, 3, 5})));
        System.out.println(Arrays.toString(s.solution(new int[]{9, 1, 5, 3, 6, 2})));
    }
}