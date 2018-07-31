package com.shiyangtao.counter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {

    ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();

    public void incr(String word) {
        map.putIfAbsent(word, new AtomicLong(0));
        map.get(word).incrementAndGet();
    }

    public static void main(String[] args) {
        AtomicTest atomicTest = new AtomicTest();
        for(int i=1;i<2000;i++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    atomicTest.incr("aaaaaaaa");
                }
            };
            thread.start();
        }

        System.out.println(atomicTest.map.get("aaaaaaaa"));
    }
}
