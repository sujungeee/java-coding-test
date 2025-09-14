package com.etc.thread;

public class ThreadTest {
    class ThirdThread extends Thread{
        @Override
        public void run() {
            System.out.println("3");
        }
    }

    class FourthThread implements Runnable{
        @Override
        public void run() {
            System.out.println("4");
        }
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();

        // 스레드 1
        FirstThread th1 = new FirstThread();
        th1.start();

        // 스레드 2
        SecondThread secthr = new SecondThread();
        Thread th2 = new Thread(secthr);
        th2.start();

        // 스레드 3
        ThirdThread th3 = test.new ThirdThread();
        th3.start();

        // 스레드 4
        Thread th4 = new Thread(test.new FourthThread());
        th4.start();

        Thread th5 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("5");
                    }
                }
        );
        th5.start();
    }
}