package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(2);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                try {
                    while (Thread.currentThread().isInterrupted()) {
                        tasks.poll().run();
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }
        for (Thread t : threads) {
            t.start();
        }

    }


    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("1"));
        threadPool.work(() -> System.out.println("2"));
        threadPool.work(() -> System.out.println("3"));
    }
}