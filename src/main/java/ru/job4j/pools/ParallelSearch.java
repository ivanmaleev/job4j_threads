package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T obj;

    public ParallelSearch(T[] array, int from, int to, T obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        if (obj == null) {
            return null;
        }
        if (from == to) {
            if (obj.equals(array[from])) {
                return from;
            }
            return null;
        }
        Integer mid = (from + to) / 2;
        ParallelSearch<T> leftSearch = new ParallelSearch<>(array, from, mid, obj);
        ParallelSearch<T> rightSearch = new ParallelSearch<>(array, mid + 1, to, obj);
        leftSearch.fork();
        rightSearch.fork();
        Integer left = leftSearch.join();
        Integer right = rightSearch.join();
        if (left != null) {
            return left;
        }
        return right;
    }

    public static <T> int search(T[] array, T obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(array, 0, array.length - 1, obj));
    }

}