package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
            pool.submit(() -> {
                String subject = "Notification " + user.getName() + " to email " + user.getEmail() + ".";
                String body = "Add a new event to " + user.getName() + System.lineSeparator();
                send(subject, body, user.getEmail());
            });
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject + body + email);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        
        EmailNotification e = new EmailNotification();
        for (int i = 0; i < 10; i++) {
            User user1 = new User("id" + i, "email");
            e.emailTo(user1);
        }
        e.close();

    }
}

class User {

    private String name;
    private String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
