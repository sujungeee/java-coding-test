package com.etc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class MyService {
    private int a, b;
    public String value;

    public MyService() {}

    public MyService(int a, int b, String value) {
        this.a = a;
        this.b = b;
        this.value = value;
    }

    private void setValue(String newValue) {
        this.value = newValue;
    }

    public int add(int a, int b) {
        return a + b;
    }
}

public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<? extends String> cls = String.class;
        // 0. Class 클래스 객체를 얻는 방법
        // Object.getClass()
        String str = new String("hi");
        Class<? extends String> cls1 = str.getClass();
        System.out.println(cls1); // class java.lang.String
        // .class 리터럴
        Class<? extends String> cls2 = String.class;
        System.out.println(cls2); // class java.lang.String
        // Class.forName()
        Class<?> cls3 = Class.forName("java.lang.management.LockInfo");
        System.out.println(cls3); // class java.lang.management.LockInfo

        System.out.println("---------------------------------");

        // 1. 클래스 정보 출력하기
        System.out.println(cls.getSimpleName()); // String
        System.out.println(cls.getPackage()); // package java.lang
        System.out.println(cls.getName()); // java.lang.String
        System.out.println(cls.toString()); // class java.lang.String
        System.out.println(cls.toGenericString()); // public final class java.lang.String

        System.out.println("---------------------------------");

        // 2. 클래스 구성 출력하기
        System.out.println(Arrays.toString(cls.getFields())); // [public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER]
        System.out.println(Arrays.toString(cls.getMethods())); // [public boolean java.lang.String.equals(java.lang.Object), public int java.lang.String.length(), public java.lang.String java.lang.String.toString(), public int java.lang.String.hashCode(), ...]
        System.out.println(Arrays.toString(cls.getInterfaces())); // [interface java.io.Serializable, interface java.lang.Comparable, interface java.lang.CharSequence, ...]
        System.out.println(cls.getSuperclass()); // class java.lang.Object

        System.out.println("---------------------------------");

        // 3. 생성자 가져와 초기화
        Class<MyService> myServiceClass = (Class<MyService>) Class.forName("com.etc.MyService");
        Constructor<MyService> constructor = myServiceClass.getConstructor(int.class, int.class, String.class);
        MyService myInstance = constructor.newInstance(3, 3, "뿡뿡이");
        System.out.println(myInstance.value); // 뿡뿡이

        System.out.println("---------------------------------");

        // 4. 메소드 가져오는 방법
        // public 메소드
        Method add = myServiceClass.getMethod("add", int.class, int.class);
        int result = (int) add.invoke(new MyService(), 3, 5);
        System.out.println(result); // 8
        // static 메소드
        // Method staticAdd = myServiceClass.getMethod("staticAdd", int.class, int.class);
        // int staticResult = (int) staticAdd.invoke(null, 3, 5);
        // System.out.println(staticResult); // 8
        // private 메소드
        // Method privateAdd = myServiceClass.getMethod("privateAdd", int.class, int.class);
        // privateAdd.getAccessible(true);
        // int privateResult = (int) privateAdd.invoke(new MyService(), 3, 5);
        // System.out.println(privateResult); // 8

        System.out.println("---------------------------------");

        // 5. 필드 가져오는 방법
        // public 필드
         Field publicValueField = myServiceClass.getField("value");
         publicValueField.set(myInstance, "헤헤헤헤");
         System.out.println(publicValueField.get(myInstance)); // 헤헤헤헤
        // static 필드
        // Field staticValueField = myServiceClass.getField("value");
        // staticValueField.set(null, "헤헤헤헤");
        // System.out.println(publicValueField.get(null)); // 헤헤헤헤
        // private 필드
        // Field privateValueField = myServiceClass.getField("value");
        // privateValueField.getAccessible(true);
        // privateValueField.set(myInstance, "헤헤헤헤");
        // System.out.println(privateValueField.get(myInstance)); // 헤헤헤헤

        System.out.println("---------------------------------");

        // 6. 예제
        try {
            Class<?> myClass = Class.forName("com.etc.MyService");
            Object instance = myClass.getDeclaredConstructor().newInstance();

            Method method = myClass.getMethod("add", int.class, int.class);
            Object result1 = method.invoke(instance, 3, 5);

            System.out.println("result: " + result1); // 8
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        } catch (NoSuchMethodException e) {
            System.out.println("메소드를 찾을 수 없습니다.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("인스턴스 생성 또는 메서드 실행 중 오류가 발생했습니다.");
        }

        try {
            Class<?> myClass = Class.forName("com.etc.MyService");
            Object instance = myClass.getDeclaredConstructor().newInstance();

            Field field = myClass.getDeclaredField("value");
            field.setAccessible(true);
            field.set(instance, "hi");
            System.out.println("value: " + field.get(instance)); // hi
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}