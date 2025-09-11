package com.programmers.sep_2025;

/**
 * 68645: 삼각 달팽이
 * # summary
 * : 달팽이를 다 채우자
 * # access
 * : 구현.... just 구현
 * 1. 왼쪽 대각선 아래로 숫자(달팽이)를 다 채운다.
 * 2. 밑부터 일직선으로 숫자를 다 채운다.
 * 3. 오른쪽 대각선 위로 숫자를 다 채운다.
 * # logic
 * startIdx: 일직선으로 채우기 위한 시작 인덱스(0)
 * lastIdx: 밑부터 채우기 위한 끝 인덱스(n - 1)
 * 1. 삼각형(List)을 다 채울 때까지 1 ~ 3번을 반복한다.
 *     1-1. 삼각형을 다 채우면, 층이 2개 내려가므로 (2 * startIdx) ~ lastIdx 까지 숫자를 채운다.
 *     1-2. (startIdx + 1)부터 (lastIdx - startIdx) 전까지 일직선으로 숫자를 채운다.
 *          -> 채워진 숫자는 건드리면 안되기 때문에 startIdx를 빼주어야 한다.
 *     1-3. lastIdx부터 (startIdx * 2) 전까지 숫자를 채운다.
 *          -> 삼각형을 완성시키는 과정이므로, 이미 숫자가 채워진 (2 * startIdx) 까지는 건드리면 안된다.
 * 2. 2차원 리스트를 다 채우면 1차원 배열로 변환하여 answer return
 */

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution68645 {
    List<int[]> layers;
    int length;

    public int[] solution(int n) {
        layers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            layers.add(new int[i + 1]);
        }
        length = (n * (n + 1)) / 2;

        // 1
        int value = 1;
        int startIdx = 0;
        int lastIdx = n - 1;
        while(value <= length) {
            // 1-1
            for (int i = startIdx * 2; i <= lastIdx; i++) {
                layers.get(i)[startIdx] = value++;
                if (value > length) break;
            }

            // 1-2
            for (int i = startIdx + 1; i < lastIdx - startIdx; i++){
                layers.get(lastIdx)[i] = value++;
                if (value > length) break;
            }

            // 1-3
            for (int i = lastIdx; i > startIdx * 2; i--) {
                layers.get(i)[i - startIdx] = value++;
                if (value > length) break;
            }

            startIdx++;
            lastIdx--;
        }

        // 2
        int[] answer = new int[length];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int[] layer = layers.get(i);
            for (int v : layer) {
                answer[idx++] = v;
            }
        }

        return answer;
    }
}

public class P68645 {
    public static void main(String[] args) {
        Solution68645 s = new Solution68645();

        System.out.println(Arrays.toString(s.solution(4)));
        System.out.println(Arrays.toString(s.solution(5)));
        System.out.println(Arrays.toString(s.solution(6)));
        System.out.println(Arrays.toString(s.solution(7)));
    }
}