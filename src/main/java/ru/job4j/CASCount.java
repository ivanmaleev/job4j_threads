package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer integer;
        do {
            integer = count.get();
        } while (!count.compareAndSet(integer, integer + 1));
    }

    public int get() {
        return count.get();
    }
}