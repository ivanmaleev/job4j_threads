package concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.start();
        Thread third = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        third.start();
        Thread fourth = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        fourth.start();
        System.out.println(Thread.currentThread().getName());
    }
}
