package com.codingTestBook.theory;

import java.util.Arrays;
import java.util.Comparator;

class Node{
    int dest, cost;

    public Node(int dest, int cost){
        this.dest= dest;
        this.cost= cost;
    }
}

public class Ch04_3_Lambda {
    public static void main(String[] args){
        Node[] nodes= new Node[5];
        nodes[0]= new Node(1, 10);
        nodes[1]= new Node(2, 20);
        nodes[2]= new Node(3,  15);
        nodes[3]= new Node(4, 5);
        nodes[4]= new Node(1, 25);
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.cost, o2.cost));

        // 위 람다식의 결과와 동일하게 정렬됨
//        Arrays.sort(nodes, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return Integer.compare(o1.cost, o2.cost);
//            }
//        });
    }
}
