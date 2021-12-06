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



        System.out.println(0);
    }

}
