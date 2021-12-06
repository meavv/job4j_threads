package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {


    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int count) {
        this.count.set(count);
    }

    public void increment() {
        int temp;
        int ref;
        try {
            do {
                ref = count.get();
                temp = count.get() + 1;
            } while (!count.compareAndSet(ref, temp));

        } catch (Exception e) {
            throw new UnsupportedOperationException("Count is not impl.");
        }

    }

    public int get() {
        try {
            return count.get();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
    }

}
