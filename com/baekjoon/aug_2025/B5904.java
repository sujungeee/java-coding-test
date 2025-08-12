// https://www.acmicpc.net/problem/5904
package com.baekjoon.aug_2025;

/**
 * 5904: Moo 게임
 * # summary
 * : Moo 수열의 N 번째 글자 구하기
 * # access
 * 1. 재귀로 길이가 N 이상인 수열의 값을 통해 답을 구하자.
 *    -> k = 0부터 단순 재귀로 문자열을 반복 호출하는 방식으로는 메모리 초과 발생
 * 2. 길이를 이용한 분할 정복 알고리즘!
 *    -> 전체 길이 중 N 번째 인덱스에서 "부분 수열의 왼쪽 or 가운데 or 오른쪽에 있는지 판별"하여 부분 수열의 인덱스로 줄여나가기
 * # logic
 * 1. findK()
 *     1-1. 몇 번째(k) 수열에서 N 번째 글자를 구할 수 있는지 구한다.
 *     1-2. k 번째에서의 전체 문자열 길이를 length에 저장한다.
 * 2. search()
 *     2-1. k 번째에서의 전체 길이(totalLength) / 가운데 길이(mid) / 왼쪽과 오른쪽의 길이(side)를 각각 구한다.
 *     2-2. 현재 idx가 부분 수열의 왼쪽에 있다면 인덱스가 같을 것이기 때문에 바로 재귀 호출한다.
 *          현재 idx가 부분 수열의 가운데에 있다면 o가 k+2개인 수열 "m o ... o" 에서 바로 인덱스를 통해 답을 구할 수 있다.
 *          현재 idx가 부분 수열의 오른쪽에 있다면 그 인덱스는 전체 길이에서 (왼쪽 + 가운데)를 뺀 인덱스일 것이기 때문에 값을 갱신하여 재귀 호출한다.
 *     2-3. cnt, 즉 k 번째가 0이 되면 "moo"에서 인덱스로 값을 구한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class B5904 {
    static int N;
    static int k;
    static List<Long> lengths;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lengths = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        // 1
        findK(0, 3);
        // 2
        System.out.println(search(k, N));
    }

    static char search(int cnt, long idx) {
        // 2-3
        if (cnt == 0) {
            return "moo".charAt((int) (idx - 1));
        }

        // 2-1
        long totalLength = lengths.get(cnt - 1);
        long mid = 3 + cnt;
        long side = (totalLength - mid) / 2;

        // 2-2
        if (idx <= side) { // 왼쪽
            return search(cnt - 1, idx);
        } else if (idx <= side + mid) { // 가운데
            String midStr = "m" + "o".repeat(cnt + 2);
            return midStr.charAt((int) (idx - side - 1));
        } else { // 오른쪽
            return search(cnt - 1, idx - (side + mid));
        }
    }

    static void findK(int idx, int length) {
        // 1-1
        if (length >= N) {
            k = idx;
            return;
        }
        // 1-2
        int newLength = 2 * length + idx + 4;
        lengths.add((long) newLength);

        findK(idx + 1, newLength);
    }
}

//public class B5904 {
//    static int N;
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//
//        String answer = search(0, "moo", 3);
//        System.out.println(answer);
//    }
//
//    static String search(int k, String prev, int length) {
//        if (length >= N) {
//            return String.valueOf(prev.charAt(N - 1));
//        }
//
//        String newStr = prev + "mo" + "o".repeat(k + 2) + prev;
//        int newLength = 2 * length + k + 4;
//        String v = search(k + 1, newStr, newLength);
//        if (v.length() == 1) return v;
//
//        return prev;
//    }
//}