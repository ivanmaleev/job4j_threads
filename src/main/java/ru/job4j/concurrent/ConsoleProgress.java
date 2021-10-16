package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        Thread progress = new Thread(new ConsoleProgress());
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
                Thread.sleep(250); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            count++;
            count = count % 4;
        }
        progress.interrupt();
    }

    public static void main(String[] args) {
        ConsoleProgress con = new ConsoleProgress();
        con.run();
    }
}
