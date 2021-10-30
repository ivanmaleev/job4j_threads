package ru.job4j.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void work() {
        ThreadPool pool = new ThreadPool();
        List<Runnable> taskList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            taskList.add(new TestRunnable());
        }
        for (Runnable task : taskList) {
            try {
                pool.work(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Runnable task : taskList) {
            try {
                pool.work(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    private static class TestRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }
}