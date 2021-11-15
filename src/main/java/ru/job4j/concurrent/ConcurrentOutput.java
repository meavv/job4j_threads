package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
        System.out.println(Thread.currentThread().getName());
    }

    class Person {

        private int age;
        private String name;


        @Override
        public String toString() {
            return "Person{" + "age=" + age
                    + ", name='" + name + '\''
                    + '}';
        }
    }
}