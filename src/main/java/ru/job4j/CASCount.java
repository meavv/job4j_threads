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
        int ref;
        do {
                ref = count.get();
            } while (!count.compareAndSet(ref, ref + 1));

    }


    public int get() {
        try {
            return count.get();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
    }

}
