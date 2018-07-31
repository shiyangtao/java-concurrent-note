package com.shiyangtao.count_down;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
