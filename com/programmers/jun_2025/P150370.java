// https://school.programmers.co.kr/learn/courses/30/lessons/150370
package com.programmers.jun_2025;

import java.util.*;

/**
 * 150370: 개인정보 수집 유효기간
 * # summary
 * : 유효기간 내에 파기되지 않은 약관 번호 출력하기
 * # logic
 * 1. 약관 종류와 유효기간을 해시로 저장
 * 2. 하나씩 확인하면서 오늘 날짜를 기준으로 수집 일자가 유효기간을 지났는지 판별
 * # 다른 방법
 * 1. 총 day 수를 계산하여 비교하기: (년 * 365) + (월 * 30) + 일
 */

class Solution150370 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        // 0
        int todayYear = Integer.parseInt(today.substring(2, 4));
        int todayMonth = Integer.parseInt(today.substring(5, 7));
        int todayDay = Integer.parseInt(today.substring(8));

        // 1
        Map<Character, Integer> termInfo = new HashMap<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            Character kind = st.nextToken().charAt(0);
            int month = Integer.parseInt(st.nextToken());
            termInfo.put(kind, month);
        }

        // 2
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            int year = Integer.parseInt(privacy.substring(2, 4));
            int month = Integer.parseInt(privacy.substring(5, 7));
            int day = Integer.parseInt(privacy.substring(8, 10));
            Character type = privacy.charAt(11);
            int period = termInfo.get(type);
            // privacy 에서 period만큼 지난게 현재 기간보다 크면 add
            // 유효기간 추가
            month += period;
            year += (month - 1) / 12;
            month = (month - 1) % 12 + 1;

            day--;
            if (day == 0) {
                day = 28;
                month--;
                if (month == 0) {
                    month = 12;
                    year--;
                }
            }
            // (년) 현재 년도보다 갱신된 년도가 더 작거나
            // (월) 현재 년도와 갱신된 년도가 같지만, 현재 달보다 갱신된 달이 더 작거나
            // (일) 현재 년, 월과 갱신된 년, 월이 같지만, 현재 일자보다 갱신된 일자가 더 작으면
            // add
            if (
                    todayYear > year ||
                    (todayYear == year && todayMonth > month) ||
                    (todayYear == year && todayMonth == month && todayDay > day)
            ) {
                answer.add(i+1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}

public class P150370 {
    public static void main(String[] args) {
        Solution150370 s = new Solution150370();

        System.out.println(Arrays.toString(s.solution(
                "2022.05.19"
                , new String[]{"A 6", "B 12", "C 3"}
                , new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})));

        System.out.println(Arrays.toString(s.solution(
                "2020.01.01"
                , new String[]{"Z 3", "D 5"}
                , new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"})));
    }
}