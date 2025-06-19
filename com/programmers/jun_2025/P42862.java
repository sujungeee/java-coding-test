// https://school.programmers.co.kr/learn/courses/30/lessons/42862
package com.programmers.jun_2025;

/**
 * 42862: 체육복
 * # summary
 * : 체육복을 입은 학생의 최댓값!
 * # access
 * 1) answer = (체육복을 1개 이상 가지고 있는 학생 수) + (도난 당한 체육복을 여분의 체육복으로 충당할 수 있는 학생 수)
 * 2) 도난 당한 체육복을 여분의 체육복으로 충당할 수 있는 학생 수 = (전체 도난 당한 학생 수) - (여분 체육복을 못 받은 학생 수)
 * 3) 2번을 구하려면
 *    => 앞부터 도난 당했는지 여부를 확인하면서, 도난 당한 학생의 양쪽에 여분의 체육복이 있다면 나눠준다!
 *    만약에 둘 다 있다면 앞의 학생의 체육복을 빌린다.
 * # logic
 * 1. answer로 체육복을 1개 이상 가지고 있는 학생 수(n - lost 길이)를 초기 설정
 *   (후에 여분 체육복을 얻은 학생 수를 더해주기)
 * 2. n 크기의 1차원 배열에 여분이 있는 학생을 1, 도난 당한 학생을 -1로 표시한다.
 *    ※ 이때, 여분이 있는 학생이 도난을 당하면 0으로 설정
 * 3. 앞부터 확인하면서 도난 당한 학생(-1)이 있다면 양쪽에 여분(1)이 있는지 체크한 후,
 *   3-1. 앞 or 뒤 한 쪽에만 여분이 있다면 해당 방향의 학생이 체육복을 나눠준다.(둘 다 0으로)
 *   3-2. 두 쪽 모두 여분이 있다면 앞 쪽 방향 학생이 체육복을 나눠준다.(둘 다 0으로)
 * 4. 도난 당한 학생을 모두 확인한 후, 전체 도난 당한 학생 수(lost 길이)에서 체육복을 얻지 못한 학생(-1) 수를 빼서 체육복을 얻은 학생 수를 구한다.
 * 5. answer에 체육복을 얻은 학생의 수를 더해주어서 return
 */

class Solution42862 {
    int[] arr;

    public int solution(int n, int[] lost, int[] reserve) {
        // 1
        int answer = n - lost.length;

        // 2
        arr = new int[n];
        for(int r : reserve) arr[r - 1] = 1;
        for(int l : lost) arr[l - 1] -= 1;

        // 3
        for (int i = 0; i < n; i++) {
            if (arr[i] == -1) {
                if (i != 0 && arr[i - 1] == 1) {
                    arr[i - 1] = 0;
                    arr[i] = 0;
                } else if (i != n - 1 && arr[i + 1] == 1) {
                    arr[i + 1] = 0;
                    arr[i] = 0;
                }
            }
        }

        // 4
        int getStudentNum = lost.length;
        for(int value : arr) {
            if (value == -1) getStudentNum--;
        }

        // 5
        return answer + getStudentNum;
    }
}

public class P42862 {
    public static void main(String[] args) {
        Solution42862 s = new Solution42862();

        System.out.println(s.solution(5, new int[] {2, 4}, new int[] {1, 3, 5}));
        System.out.println(s.solution(5, new int[] {2, 4}, new int[] {3}));
        System.out.println(s.solution(3, new int[] {3}, new int[] {1}));
    }
}