package ru.job4j;

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
        CASTest casTest = new CASTest();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casTest.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                casTest.increment();
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

        System.out.println(casTest.get());
    }

}
