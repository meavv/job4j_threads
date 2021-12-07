package ru.job4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CASTest {

    private int count = 0;

    public void increment() {
        int temp;
        int ref;
        do {
                ref = count;
                temp = count + 1;
        } while (!check(ref, temp));

    }

    public int get() {
        return count;
    }

    public boolean check(int ref, int temp) {
        if (count == ref) {
            count = temp;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        pool.submit(() -> System.out.println("Execute " + Thread.currentThread().getName()));
        pool.submit(() -> System.out.println("Execute " + Thread.currentThread().getName()));

        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }


}
