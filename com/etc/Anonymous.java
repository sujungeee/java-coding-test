package com.etc;

import java.util.Comparator;

class Subject {
    public void exam() {
        System.out.println("시험을 봅니다.");
    }
}

interface Calculator {
    int operate(int a, int b);
}

public class Anonymous {
    static void add(Calculator calc) {
        int result = calc.operate(3, 4);
        System.out.println(result); // 7
    }

    public static void main(String[] args) {
        // 1. 익명 클래스를 함수의 인자로 전달
        add(new Calculator() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        });

        Calculator multiply = getOperation("*");
        System.out.println(multiply.operate(3, 5)); // 15
    }

    // 2. 익명 클래스를 함수의 반환 값으로 전달
    static Calculator getOperation(String op) {
        return new Calculator() {
            @Override
            public int operate(int a, int b) {
                switch (op) {
                    case "+":
                        return a + b;
                    case "-":
                        return a - b;
                    case "*":
                        return a * b;
                    case "/":
                        return b != 0 ? a / b : 0;
                    default:
                        return 0;
                }
            }
        };
    }
}