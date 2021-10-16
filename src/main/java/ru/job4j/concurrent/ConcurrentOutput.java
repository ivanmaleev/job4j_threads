package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.start();
        Thread third = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        third.start();
        while (second.getState() != Thread.State.TERMINATED || third.getState() != Thread.State.TERMINATED) {
            continue;
        }
        System.out.println("finish");
    }
}
