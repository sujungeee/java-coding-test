package com.programmers.sep_2025;

/**
 * 68646: 풍선 터트리기
 * # summary
 * : 끝까지 살아남을 수 있는 풍선의 개수를 구하자.
 * # access
 * 1. 한 풍선을 기준으로, 양쪽 side 중 기준 풍선보다 작은 값이 최대 1개 있어야 한다.
 * => 이유: 양쪽 중 가장 작은 값이 살아 남아야, 마지막에 작은 값을 최대 1번 터트릴 수 있기 때문
 * 2. 양쪽 끝은 무조건 최후에 살아남을 수 있다.
 * => 큰 값이든 작은 값이든, 한쪽만 비교하면 되기 때문
 * # logic
 * 1. 0번부터 i번까지의 가장 작은 값을 저장하는 leftMins와,
 *    (N - 1)번부터 i번까지의 가장 작은 값을 저장하는 rightsMin을 초기화한다.
 * => i번을 기준으로 0 ~ (i - 1)까지의 가장 작은 값과 (i + 1) ~ (N - 1)까지의 작은 값을 확인하기 위함
 * 2. 무조건 최후에 살아남을 수 있는 양쪽 풍선이 있으므로 answer = 2로 초기화한다.
 * 3. i를 기준으로 양쪽의 최솟값이 각각 i보다 작다면 -> 최후에 살아남을 수 없다.
 *    따라서 위 조건을 만족하지 않는다면 최후에 살아남는 풍선의 수를 +1 한다.
 */

class Solution68646 {
    int N;
    int[] leftMins;
    int[] rightMins;

    public int solution(int[] a) {
        N = a.length;
        // 1
        leftMins = new int[N];
        rightMins = new int[N];

        leftMins[0] = a[0];
        rightMins[N - 1] = a[N - 1];

        for (int i = 1; i < N; i++) {
            leftMins[i] = Math.min(a[i], leftMins[i - 1]);
            rightMins[N - i - 1] = Math.min(a[N - i - 1], rightMins[N - i]);
        }

        // 2
        int answer = 2;

        // 3
        for (int i = 1; i < a.length - 1; i++) {
            if (!(leftMins[i - 1] < a[i] && rightMins[i + 1] < a[i])) answer++;
        }

        return answer;
    }
}

public class P68646 {
    public static void main(String[] args) {
        Solution68646 s = new Solution68646();

        System.out.println(s.solution(new int[] {9,-1,-5}));
        System.out.println(s.solution(new int[] {-16,27,65,-2,58,-92,-71,-68,-61,-33}));
    }
}