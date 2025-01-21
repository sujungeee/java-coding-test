// https://www.acmicpc.net/problem/15681
// 15681: 트리와 쿼리
package com.baekjoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15181 {
    public static int N, R, Q;
    public static List<List<Integer>> graph;
    public static Tree tree;
    public static boolean[] visited;

    public static class Node {
        int data;
        List<Node> children;

        public Node(int data) {
            this.data= data;
            this.children= new ArrayList<>();
        }

        public void addChild(Node child) {
            this.children.add(child);
        }
    }

    public static class Tree {
        Node root;

        public Tree(int rootData) {
            root= new Node(rootData);
        }

        public int traversePreOrder(Node node, int cnt) {
            if (node != null) {
                for(Node child: node.children) {
                    cnt= traversePreOrder(child, cnt+1);
                }
            } else {
                return cnt;
            }
            return cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        R= Integer.parseInt(st.nextToken());
        Q= Integer.parseInt(st.nextToken());

        graph= new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++) {
            st= new StringTokenizer(br.readLine());
            int start= Integer.parseInt(st.nextToken());
            int end= Integer.parseInt(st.nextToken());
            // 양방향
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        visited= new boolean[N+1];
        makeTree();

        for(int i=0; i<Q; i++) {
            int u= Integer.parseInt(br.readLine());
            System.out.println(sub_cnt(u));
        }
    }

    public static void makeTree() {
        tree= new Tree(R);
        buildTree(tree.root);
    }

    public static void buildTree(Node curNode) {
        visited[curNode.data]= true;
        for(int neighbor: graph.get(curNode.data)) {
            if (!visited[neighbor]){
                Node cNode= new Node(neighbor);
                curNode.addChild(cNode);
                buildTree(cNode);
            }
        }
    }

    public static int sub_cnt(int u) {
        Node node= find_node(tree.root, u);
        return tree.traversePreOrder(node, 1);
    }

    public static Node find_node(Node pNode, int nodeData) {
        if (pNode.data == nodeData) {
            return pNode;
        }
        for(Node cNode: pNode.children) {
            Node fNode= find_node(cNode, nodeData);
            if (fNode != null) {
                return fNode;
            }
        }
        return null;
    }
}