package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread timer = new Thread(() -> {
            for (int index = 0; index < 101; index++) {
                System.out.print("\rLoading : " + index + "%");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\rLoading finished");
        });
        timer.start();
    }
}
