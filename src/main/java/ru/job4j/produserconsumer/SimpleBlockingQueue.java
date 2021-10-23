package ru.job4j.produserconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int maxSize;
    private boolean done = false;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        notifyAll();
        queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            if (done) {
                return null;
            }
            wait();
        }
        notifyAll();
        return queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean getDone() {
        return done;
    }

    public synchronized void setDone(boolean done) {
        this.done = done;
    }
}