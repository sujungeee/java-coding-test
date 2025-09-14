package com.etc.thread;

public class SecondThread implements Runnable{
    @Override
    public void run() {
        System.out.println("2");
    }
}