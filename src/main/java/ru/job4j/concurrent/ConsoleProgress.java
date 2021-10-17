package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        Thread progress = new Thread();
        progress.start();
        int count = 0;
        String simb = "";
        while (!Thread.currentThread().isInterrupted()) {
            switch (count) {
                case 0:
                    simb = "|";
                    break;
                case 1:
                    simb = "/";
                    break;
                case 2:
                    simb = "-";
                    break;
                case 3:
                    simb = "\\";
                    break;
                default:
                    break;
            }
            System.out.print("\r loading ... " + simb);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            count++;
            count = count % 4;
        }
    }


    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
