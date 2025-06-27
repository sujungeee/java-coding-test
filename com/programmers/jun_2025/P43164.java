// https://school.programmers.co.kr/learn/courses/30/lessons/43164
package com.programmers.jun_2025;

/**
 * 43164: 여행경로
 * # summary
 * : ICN 공항으로부터 갈 수 있는 경로 나열하기
 * # access
 * 1. dfs! 목적지가 곧 출발지가 되는 공항을 찾아야 한다.
 * 2. 만약, 목적지가 둘 이상이면 알파벳 순으로 앞선 공항을 목적지로 택한다.
 *   ※ 알파벳 순으로 앞선 공항을 선택할 때 티켓을 모두 소진하지 못한다면, 그 뒤의 공항을 선택해야 한다.
 * # logic
 * 1. tickets를 정렬하여 알파벳 순으로 앞선 공항 순으로 정렬한다.
 * 2. recur(): start(출발 공항) / cnt(티켓 소진 개수)
 *     2-1. cnt(티켓 소진 개수)가 ticketCnt(전체 티켓 개수)가 되면 재귀 탈출 조건을 활성화! (finished = true)
 *          , return 되어서 finished가 true이면 재귀 함수 탈출
 *     2-2. for 문을 돌면서 출발 공항이 start와 일치하고, 해당 티켓을 사용하지 않았다면
 *         → 해당 티켓 사용(visited[i]), answer에 도착 공항 추가
 *         → 다음 도착 공항을 찾기 위해 재귀 호출(recur())
 */

import java.util.*;

class Solution43164 {
    List<String> answers;
    List<String[]> paths; // 티켓 경로
    int ticketCnt; // 티켓 개수
    boolean finished; // 티켓을 모두 사용했는지 여부
    boolean[] visited; // 티켓을 사용했는지 여부

    public String[] solution(String[][] tickets) {
        // 1
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            } else {
                return a[0].compareTo(b[0]);
            }
        });

        answers = new ArrayList<>();
        answers.add("ICN");
        paths = Arrays.asList(tickets);
        ticketCnt = tickets.length;
        finished = false;
        visited = new boolean[ticketCnt];

        recur("ICN", 0);

        return answers.toArray(new String[0]);
    }

    public void recur(String start, int cnt) {
        // 2-1
        if (cnt == ticketCnt) {
            finished = true;
            return;
        }

        // 2-2
        for (int i = 0; i < paths.size(); i++) {
            if (!visited[i] && paths.get(i)[0].equals(start)) {
                visited[i] = true;
                answers.add(paths.get(i)[1]);

                recur(paths.get(i)[1], cnt + 1);
                if (finished) return;

                visited[i] = false;
                answers.remove(answers.size() - 1);
            }
        }
    }
}

public class P43164 {
    public static void main(String[] args) {
        Solution43164 s = new Solution43164();

        System.out.println(Arrays.toString(s.solution(new String[][]{
                {"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}
        })));
        System.out.println(Arrays.toString(s.solution(new String[][]{
                {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}
        })));
        System.out.println(Arrays.toString(s.solution(new String[][]{
                {"ICN", "BBB"}, {"BBB", "ICN"}, {"ICN", "AAA"}
        })));
    }
}