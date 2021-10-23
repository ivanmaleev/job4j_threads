package ru.job4j;

import ru.job4j.produserconsumer.SimpleBlockingQueue;

public class ConsumerProducer {

    public static SimpleBlockingQueue<String> queue;
    public static String[] mes;

    public static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < mes.length; i++) {
                queue.offer(mes[i]);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                String poll = queue.poll();
                System.out.println(poll);
                if (poll.equals("done")) {
                    return;
                }
            }
        }
    }

    public static void setQueue(SimpleBlockingQueue<String> queue) {
        ConsumerProducer.queue = queue;
    }

    public static void setMes(String[] mes) {
        ConsumerProducer.mes = mes;
    }

    public static SimpleBlockingQueue<String> getQueue() {
        return queue;
    }
}
