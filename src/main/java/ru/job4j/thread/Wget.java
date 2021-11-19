package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;


public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Введите 2 агрумента");
        }
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(url.substring(url.lastIndexOf("/") + 1))) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            var startTime = Instant.now();
            long bytesWrited = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWrited = bytesWrited + bytesRead;
            }
            var endTime = Instant.now();
            var trueTime = bytesWrited / speed;
            var time = Duration.between(startTime, endTime).toSeconds();
            var diffTime = trueTime - time;
            if (trueTime > time) {
                Thread.sleep(diffTime * 1000);
            }
            System.out.println("Размер " + bytesWrited);
            System.out.println("Секунд на скачивание " + time);
            System.out.println("Секунд на скачивание с заданной скоростью " + trueTime);
            System.out.println("Задержка " + diffTime);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Wget.validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}