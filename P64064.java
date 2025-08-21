// https://school.programmers.co.kr/learn/courses/30/lessons/64064

/**
 * 64064: 불량 사용자
 * # summary
 * : 불량 사용자 리스트로부터 제재 아이디 목록의 수를 구하자.
 * # access
 * 1. 조합: 단순 조합으로는 순서에 따른 매칭을 진행하기 어려움
 * 2. 백트래킹: 순서에 맞춰 매칭을 진행하되, 중복 집합은 제거하기(by Set)
 * # logic
 * 1. 선택될 응모자 아이디(선택되지 않은 응모자)가 불량 사용자 아이디(idx)와 매칭되면,
 *    응모자를 뽑고 selected에 추가한 후 -> 다음 응모자를 탐색(search)한다.
 * 2. 응모자 몇을 불량 사용자의 모든 아이디와 매칭하면 집합으로 변환해 Set에 추가한다.(중복된 조합은 배제하기 위함)
 * 3. isMatch(): 특정 응모자의 아이디(user)와 특정 불량 사용자의 아이디(ban)가 매칭되는지 확인하는 함수
 *    => 각 문자열이 같거나 ban 문자열이 * 이어야 한다. 이외에는 매칭되지 않는다!
 * 4. 가능한 경우의 수를 출력한다.
 */

import java.util.*;

class Solution64064 {
    String[] user_ids;
    String[] banned_ids;
    Set<Set<Integer>> answer;
    int n, k;

    public int solution(String[] user_id, String[] banned_id) {
        user_ids = user_id;
        banned_ids = banned_id;
        answer = new HashSet<>();
        n = user_id.length;
        k = banned_id.length;
        boolean[] visited = new boolean[n];

        search(visited, 0, new ArrayList<>());

        // 4
        return answer.size();
    }

    public void search(boolean[] visited, int idx, List<Integer> selected) {
        // 2
        if (idx == k) {
            answer.add(new HashSet<>(selected));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 1
            if (!visited[i] && isMatch(user_ids[i], banned_ids[idx])) {
                visited[i] = true;
                selected.add(i);
                search(visited, idx + 1, selected);
                selected.remove(selected.size() - 1);
                visited[i] = false;
            }
        }
    }

    // 3
    public boolean isMatch(String user, String ban) {
        if (user.length() != ban.length()) return false;
        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i) != '*' && ban.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
}

public class P64064 {
    public static void main(String[] args) {
        Solution64064 s = new Solution64064();

        System.out.println(s.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "abc1**"}));
        System.out.println(s.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"*rodo", "*rodo", "******"}));
        System.out.println(s.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"}));
    }
}