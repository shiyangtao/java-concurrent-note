package com.shiyangtao.future_task;

public class MyThread extends Thread {
    public int i = 0;

    public MyThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
        int result = 0;
        for (int i = 0; i < 100; ++i) {
            result += i;
        }

        i = result;

    }

}
