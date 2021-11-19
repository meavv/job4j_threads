package ru.job4j.thread;

public class Test {
    public static void main(String[] args) {
        String rsl = "https://proof.ovh.net/files/10Mb.dat";
        rsl = rsl.substring(rsl.lastIndexOf("/") + 1);
        System.out.println(rsl);
    }
}
