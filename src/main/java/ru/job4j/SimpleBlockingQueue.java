package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;
    private int count;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }


    public synchronized void offer(T value) throws InterruptedException {
        while (count >= size) {
            wait();
            System.out.println("Wait offer");
        }
        queue.add(value);
        count++;
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (count == 0) {
            wait();
            System.out.println("Wait poll");
        }
        notifyAll();
        count--;
        return queue.poll();
    }

    public int getSize() {
        return size;
    }
}