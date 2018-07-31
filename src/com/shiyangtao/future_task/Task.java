package com.shiyangtao.future_task;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
        int result = 0;
        for (int i = 0; i < 100; ++i) {
            result += i;
        }

        Thread.sleep(3000);
        return result;
    }
}
