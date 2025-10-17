package com.etc;

import java.util.*;

class A<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Generic {
    public static void main (String[] args) {
        A<String> a = new A<>();
        a.setValue("Hello");
        String strA = a.getValue();
        System.out.println(strA);

        A<?> a1 = new A<String>();
        Object value = a1.getValue();
        System.out.println(value);

    }
}