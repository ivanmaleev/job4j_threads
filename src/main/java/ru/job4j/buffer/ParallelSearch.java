package ru.job4j.buffer;

import ru.job4j.produserconsumer.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()
                            || queue.size() != 0 || !queue.getDone()) {
                        Integer poll = null;
                        try {
                            poll = queue.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (poll != null) {
                            System.out.println(poll);
                        }
                    }
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 300; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    queue.setDone(true);
                }
        );
        producer.start();
        producer.join();
        consumer.interrupt();
    }
}