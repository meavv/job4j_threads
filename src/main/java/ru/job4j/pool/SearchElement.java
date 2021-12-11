package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchElement extends RecursiveTask<Integer> {

    private final int[] array;
    private final int index;
    private final int from;
    private final int to;

    public SearchElement(int[] array, int index, int from, int to) {
        this.array = array;
        this.index = index;
        this.from = from;
        this.to = to;
    }


    @Override
    protected Integer compute() {
        if (to - from < 10) {
            for (int i = from; i <= to; i++) {
                if (array[i] == index) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        SearchElement l = new SearchElement(array, index, from, mid);
        SearchElement r = new SearchElement(array, index, mid + 1, to);
        l.fork();
        r.fork();
        return Math.max(l.join(), r.join());
    }

    public static Integer search(int[] array, int index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SearchElement(array, index, 0, array.length - 1));
    }

}