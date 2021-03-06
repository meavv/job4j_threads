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

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }


    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
            System.out.println("Wait offer");
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        notifyAll();
        return queue.poll();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}