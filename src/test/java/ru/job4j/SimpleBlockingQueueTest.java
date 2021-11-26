package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(obj1);
        arrayList.add(obj2);
        arrayList.add(obj3);
        arrayList.add(obj4);
        SimpleBlockingQueue<Object> simpleBlockingQueue = new SimpleBlockingQueue<>(3);
        Thread addThread = new Thread(() -> {
            try {
                int count = 1;
                for (Object o : arrayList) {
                    simpleBlockingQueue.offer(o);
                    System.out.println("Добалвен объект " + count++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread getThread = new Thread(() -> {
            try {
                int count = 1;
                while (simpleBlockingQueue.getSize() != 0) {
                    simpleBlockingQueue.poll();
                    System.out.println("Получен объект " + count++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        addThread.start();
        getThread.start();
        addThread.join();
        getThread.join();
    }

}