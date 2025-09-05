package com.etc.functionProgramming;

import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

// Currying은 언젠가...
public class funcP {
    static IntConsumer print = System.out::println;
    static IntPredicate isEven = (n) -> n % 2 == 0;
    static IntPredicate isGraterThanFive = (n) -> n > 5;

    public static void main(String[] args) {
        iterator.accept(0, 10);

        fori.accept(0, 10, (n)-> {
            return n % 2 == 0;
        });

        foriaction.accept(1, 10, isEven, print);
        foriaction.accept(1, 10, isGraterThanFive, print);
    }

    static BiConsumer<Integer, Integer> iterator = (start, end) -> {
        for (int n = start; n <= end; n++) {
            if (n % 2 == 0) {
                System.out.println(n);
            }
        }
    };

    static ThreeConsumer<Integer, Integer, IntPredicate> fori = (startNum, endNum, validator) -> {
        for (int i = startNum; i <= endNum; i++) {
            if (validator.test(i)) {
                System.out.println(i);
            }
        }
    };

    static FourConsumer<Integer, Integer, IntPredicate, IntConsumer> foriaction = (startNum, endNum, validator, action) -> {
        for (int i = startNum; i < endNum; i++) {
            if (validator.test(i)) {
                action.accept(i);
            }
        }
    };
}
