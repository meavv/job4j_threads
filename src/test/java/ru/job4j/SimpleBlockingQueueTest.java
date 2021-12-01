package ru.job4j;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {


    @Test
    public void offer() throws InterruptedException {
        final CopyOnWriteArrayList<Object> buffer = new CopyOnWriteArrayList<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(obj1);
        arrayList.add(obj2);
        arrayList.add(obj3);
        SimpleBlockingQueue<Object> simpleBlockingQueue = new SimpleBlockingQueue<>(4);
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
                    buffer.add(simpleBlockingQueue.poll());
                    System.out.println("Получен объект " + count++);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        addThread.start();
        getThread.start();
        addThread.join();
        getThread.interrupt();
        getThread.join();
        assertThat(buffer, is(Arrays.asList(obj1, obj2, obj3)));
    }

    @Test
    public void whenFetchAllThenGetIt()  {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        try {
            consumer.start();
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

}