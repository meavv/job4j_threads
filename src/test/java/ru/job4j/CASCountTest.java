package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;


public class CASCountTest {

    @Test
    public void whenTest() {
        CASCount casCount = new CASCount(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casCount.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casCount.increment();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(20, casCount.get());
    }

}