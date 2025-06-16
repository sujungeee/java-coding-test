// https://school.programmers.co.kr/learn/courses/30/lessons/388353
package com.programmers.jun_2025;

import java.util.ArrayList;
import java.util.List;

/**
 * 388353: 지게차와 크레인
 * # summary
 * : 지게차 or 크레인으로 컨테이너를 모두 뽑고, 남은 컨테이너 개수 return
 *
 * # access
 * 1. just 구현 문제
 *  1-1. 전체 배열에서 지게차나 크레인으로 인해 삭제되는 컨테이너의 좌표(외부로 접근이 가능한 좌표)는 .로 표시
 *  1-2. . 개수 return
 * 2. 추가 고려해야할 사항
 *  2-1. 크레인으로 문자가 삭제되었을 때는 삭제된 좌표가 외부로부터 접근이 가능한지 따진다.
 *  2-2. 외부로 접근이 안될 경우는 ,로 표시한다.
 *       (이유: 해당 좌표는 다른 컨테이너의 외부로 인식되지 못해 구분하여 표시하기 위해서)
 *  2-3. 출고 요청으로 컨테이너들이 치워진 후에는, 외부로 접근이 가능해질 수 있으므로 이를 고려해야 한다.
 *
 * # logic
 * 1. storage를 char형 2차원 배열(containers)로 바꾸기!
 * 2. requests에 따라 컨테이너 지우기
 *     2-1. request가 한 문자(s)라면 각 컨테이너의 동서남북이 가장자리 이거나 .인 컨테이너 중 문자(s)가 적혀있는 컨테이너 모두 '.'로 지우기.
 *     2-2. request가 두 문자(ss)라면 모든 컨테이너에서 문자(s)가 있는 컨테이너 모두 지우기.
 *         단, 외부로의 접근이 가능한 위치면 '.', 외부로의 접근이 가능하지 않으면 ',' 로 지우기.
 *     2-3. request로 지우고, 외부 접근이 안되던 위치가 외부 접근이 가능하게 되면 ',' -> '.' 로 바꾸기.
 * 3. 컨테이너 내에 '.'과 ','이 아닌 문자의 개수 return
 */

class Solution388353 {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    char[][] containers;
    int n, m;

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();

        // 1
        containers = new char[n][m];
        for (int i = 0; i < n; i++) {
            containers[i] = storage[i].toCharArray();
        }

        for (String request: requests) {
            char c = request.charAt(0);
            List<int[]> applys = new ArrayList<>();
            if (request.length() == 1) {
                // 2-1
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (containers[i][j] == c) {
                            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                                applys.add(new int[]{i, j});
                                continue;
                            }
                            for (int k = 0; k < 4; k++) {
                                int x = i + dx[k];
                                int y = j + dy[k];
                                if (containers[x][y] == '.') {
                                    applys.add(new int[]{i, j});
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int[] apply : applys) {
                    containers[apply[0]][apply[1]] = '.';
                }
            } else {
                // 2-2
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (containers[i][j] == c) {
                            boolean isAccess = false; // 외부로부터 접근이 가능한 좌표인지 여부
                            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                                containers[i][j] = '.';
                                isAccess = true;
                            } else {
                                for (int k = 0; k < 4; k++) {
                                    int x = i + dx[k];
                                    int y = j + dy[k];
                                    if (containers[x][y] == '.') {
                                        containers[i][j] = '.';
                                        isAccess = true;
                                        break;
                                    }
                                }
                            }
                            if(!isAccess) {
                                containers[i][j] = ',';
                            }
                        }
                    }
                }
            }

            // 2-3
            boolean changed; // 외부 접근이 가능한 좌표로 인해 특정 좌표가 외부 접근 X -> 외부 접근 O 이 되었는지 여부
            do {
                changed = false;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (containers[i][j] == ',') {
                            for (int k = 0; k < 4; k++) {
                                int x = i + dx[k];
                                int y = j + dy[k];
                                if (x >= 0 && x < n && y >= 0 && y < m && containers[x][y] == '.') {
                                    containers[i][j] = '.';
                                    changed = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            } while (changed);
        }

        // 3
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (containers[i][j] != '.' && containers[i][j] != ',') {
                    answer++;
                }
            }
        }
        return answer;
    }
}

public class P388353 {
    public static void main(String[] args) {
        Solution388353 s = new Solution388353();

        System.out.println(s.solution(new String[] {"AZWQY", "CAABX", "BBDDA", "ACACA"}, new String[] {"A", "BB", "A"}));
        System.out.println(s.solution(new String[] {"HAH", "HBH", "HHH", "HAH", "HBH"}, new String[] {"C", "B", "B", "B", "B", "H"}));
    }
}