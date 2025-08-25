package com.programmers.aug_2025;

/**
 * 12938: 최고의 집합
 * # summary
 * : 합이 s가 되는 n개로 이루어진 집합 중 가장 곱이 큰 집합을 구하자.
 * # access
 * 1. 완전탐색 + 백트래킹: n은 최대 1만, s는 최대 1억
 * => 시간 초과이다..
 * 2. 최대의 곱은 가급적 작은 수 * 큰 수의 집합보다 -> 작은 수와 큰 수의 중간 값들의 집합이 곱한 값이 더 크다.
 *    예를 들어, n이 2, s가 8일 때 -> 1 * 7 보다 4 * 4가 값이 훨씬 크다.
 *    따라서, 중간 값들을 최대한 균등 분배해서 n에 나눠서 저장해야 한다.
 * # logic
 * 1. 만약 n개보다 더한 값(s)이 작으면, n개로 s를 이룰 수 없기 때문에 최고의 집합이 존재하지 않으므로 -1을 return한다.
 * 2. 몫은 "균등 분배를 위해 모두가 최소한으로 가져야 하는 값"이므로 answer에 quotient 값을 모두 채운다.
 * 3. 이후, 남은 값(remainder)들을 answer에 오름차순으로 채워야 하기 때문에 끝 값부터 +1씩 더해서 마저 s를 채운다.
 */

import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;

class Solution12938_1 {
    public int[] solution(int n, int s) {
        // 1
        if (n > s) return new int[] {-1};
        int[] answer = new int[n];

        int quotient = s / n; // 몫
        int remainder = s % n; // 나머지

        // 2
        Arrays.fill(answer, quotient);

        // 3
        int idx = n - 1;
        for (int i = remainder; i > 0; i--) {
            answer[idx--]++;
        }

        return answer;
    }
}

// 완전 탐색 + 백트래킹 --> 되겠냐(n <= 10000)

class Solution12938_2 {
    int N, sum;
    int[] answer;
    int maxValue;

    public int[] solution(int n, int s) {
        N = n;
        sum = s;
        answer = new int[N];
        maxValue = 0;

        List<Integer> set = new ArrayList<>();
        recur(set, 0, 0);

        if (answer[0] == 0) return new int[] {-1};
        return answer;
    }

    public void recur(List<Integer> arr, int cnt, int total) {
        if (cnt == N) {
            int value = 1;
            for (int v : arr) value *= v;
            if (value > maxValue) {
                answer = arr.stream().mapToInt(Integer::intValue).toArray();
                maxValue = value;
            }
            return;
        }

        int startValue = (!arr.isEmpty()) ? arr.get(arr.size() - 1) : 1;
        for (int i = startValue; i <= sum - total; i++) {
            arr.add(i);
            recur(arr, cnt + 1, total + i);
            arr.remove(arr.size() - 1);
        }
    }
}

public class P12938 {
    public static void main(String[] args) {
        Solution12938_1 s = new Solution12938_1();

        System.out.println(Arrays.toString(s.solution(2, 9)));
        System.out.println(Arrays.toString(s.solution(2, 1)));
        System.out.println(Arrays.toString(s.solution(2, 8)));
        System.out.println(Arrays.toString(s.solution(3, 8)));
    }
}