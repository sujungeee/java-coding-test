// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV-Un3G64SUDFAXr&categoryId=AV-Un3G64SUDFAXr&categoryType=CODE&problemTitle=%EB%AC%B8%EC%9E%90%EC%97%B4+%EA%B5%90%EC%A7%91%ED%95%A9&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
package com.swea.course.jul_2025;

/**
 * 2948: 문자열 교집합
 * # summary
 * : 두 집합에 모두 속하는 문자열 원소의 개수
 * # access
 * : 각 집합에 대한 해시 테이블을 만들고, 한 해시테이블을 순회하며 다른 해시테이블에도 똑같은 key와 value가 있다면 +1
 * # logic
 * 1. 각 집합에 대해서 해시 테이블 생성(key: 주어진 문자열, value: 문자열의 각 문자에 대해 아스키코드를 더한 값)
 * 2. 하나의 해시 테이블에서 key 값을 대상으로 다른 해시 테이블에도 key가 있고, 그 value가 동일하다면
 *    => 두 집합에 모두 속하는 것이므로 answer +1
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

public class S2948 {
    static Map<String, Integer> map1;
    static Map<String, Integer> map2;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            answer = 0;
            map1 = new HashMap<>();
            map2 = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 1
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                String str = st.nextToken();
                int value = 0;
                for (char c : str.toCharArray()) {
                    value += c;
                }
                map1.put(str, value);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                String str = st.nextToken();
                int value = 0;
                for (char c : str.toCharArray()) {
                    value += c;
                }
                map2.put(str, value);
            }

            // 2
            for (String key : map1.keySet()) {
                if (map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
                    answer++;
                }
            }

            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}