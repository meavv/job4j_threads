package ru.job4j.thread;

import javax.xml.transform.Templates;
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
            long bytesWrite = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWrite = bytesWrite + bytesRead;
                if (bytesWrite >= speed) {
                    var endTime1 = Instant.now();
                    var time = Duration.between(startTime, endTime1).toMillis();
                    if (time < 1000) {
                    Thread.sleep(1000 - time);
                    }
                    bytesWrite = 0;
                    startTime = Instant.now();
                }

            }
            var endTime = Instant.now();
            var time = Duration.between(startTime, endTime).toSeconds();
            System.out.println("Секунд на скачивание " + time);
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