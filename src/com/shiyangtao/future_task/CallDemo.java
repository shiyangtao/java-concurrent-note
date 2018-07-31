package com.shiyangtao.future_task;

import java.util.concurrent.*;

public class CallDemo {
    public static void main(String[] args) {


        //***************** 第一种方式:Future + ExecutorService *********************

        try {
            Task task = new Task();
            ExecutorService service = Executors.newCachedThreadPool();
            Future<Integer> future = service.submit(task);
            System.out.println("第一种"+future.get());
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //***************** 第二种方式:FutureTask + ExecutorService

        try {
            Task task = new Task();
            FutureTask futureTask = new FutureTask(task);
            ExecutorService service2 = Executors.newCachedThreadPool();
            Future future2 = service2.submit(futureTask);
            System.out.println("第二种"+future2.get());// 这种方式取不出来
            System.out.println("第二种"+futureTask.get());
            service2.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //**************** 第三种方式:FutureTask + Thread
        try {
            Task task = new Task();
            FutureTask<Integer> futureTask = new FutureTask(task);

            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println("第三种"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //**************** 第四种方式:FutureTask(Runable,result) + Thread
        try {
            Result r = new Result();
            FutureTask<Result> futureTask = new FutureTask<Result>(
                    () -> {
                        int result = 0;
                        for (int i = 0; i < 100; ++i) {
                            result += i;
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        r.i = result;
                    }
                    , r);
            Thread thread = new Thread(futureTask);
            thread.start();

            System.out.println("第四种"+futureTask.get().i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // 五
        try {
            MyThread myThread = new MyThread(0);
            FutureTask<MyThread> futureTask = new FutureTask<MyThread>(myThread
                    , myThread);
            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println("第五种"+"@@@@@@@@@@" + myThread.i);
            Thread.sleep(3000);
            System.out.println("第五种"+"@@@@@@@@@@" + myThread.i);
            System.out.println("第五种"+futureTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 六
        try {
            MyThread myThread = new MyThread(0);
            myThread.start();
            System.out.println("第六种"+"@@@@@@@@@@" + myThread.i);
            Thread.sleep(3000);
            System.out.println("第六种"+"@@@@@@@@@@" + myThread.i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}

class Result {
    int i = 0;
}
