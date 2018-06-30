package com.shiyangtao.synch_reentrant;

public class Child extends Father{
    public static void main(String[] args) {
        new Child().doSomething();

    }

    public synchronized void doSomething(){
        System.out.println("child");
        super.doSomething();
    }

}

class Father{
    public synchronized void doSomething(){
        System.out.println("Father");
    }
}
