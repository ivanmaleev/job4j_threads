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

    public synchronized void offer(T value) {
        while (queue.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (queue.size() == 0) {
            notifyAll();
        }
        queue.offer(value);
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            if (done) {
                return null;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (queue.size() == maxSize) {
            notifyAll();
        }
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