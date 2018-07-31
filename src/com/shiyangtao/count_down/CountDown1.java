package com.shiyangtao.count_down;

import java.util.concurrent.CountDownLatch;

public class CountDown1 {
    public void timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        System.out.println("await");
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            System.out.println("countDown");
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        System.out.println("start="+start);
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("end="+end);
        System.out.println(end - start);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDown1 countDown1 = new CountDown1();
        countDown1.timeTasks(10,new MyThread());
    }
}
