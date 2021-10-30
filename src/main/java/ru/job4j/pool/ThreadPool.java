package ru.job4j.pool;

import ru.job4j.produserconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool implements Runnable {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks =
            new SimpleBlockingQueue<>(10);
    private volatile boolean isRunning = false;

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(new Task());
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private class Task implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = tasks.poll();
                    if (task != null) {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}