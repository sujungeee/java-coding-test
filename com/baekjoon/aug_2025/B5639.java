// https://www.acmicpc.net/problem/5639
package com.baekjoon.aug_2025;

/**
 * 5639: 이진 검색 트리
 * # summary
 * : 전위 순회로 이진 검색 트리를 만들어 후위 순회로 변환하자.
 * # access
 * 1. 전위 순회로 BST 생성
 * 2. 재귀로 후위 순회하여 출력하기.
 * # logic
 * 1. 전위 순회이므로 첫 입력을 루트 노드로 설정하고, 그 다음 입력부터 루트 노드 하위로 이진 검색 트리를 삽입한다.
 * 2. 루트 노드보다 작은 값은 왼쪽 서브트리, 큰 값은 오른쪽 서브트리로 확장시킨다.
 *    만약 서브트리가 비어있다면 해당 위치에 입력 값을 넣는다.
 * 3. 완성된 트리로 후위 순회를 한다. (by postorder())
 *    말단 노드이면 return / 왼쪽 탐색 -> 오른쪽 탐색 -> 본인의 노드 출력을 재귀로 반복하며 순회한다.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B5639 {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }

        // 2
        void insert(int n) {
            if (n < this.val) {
                if (this.left == null) { this.left = new Node(n); }
                else { this.left.insert(n); }
            } else {
                if (this.right == null) { this.right = new Node(n); }
                else { this.right.insert(n); }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        Node root = new Node(Integer.parseInt(line));

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) break;
            root.insert(Integer.parseInt(line));
        }

        // 3
        postorder(root);
    }

    static void postorder(Node node) {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.println(node.val);
    }
}