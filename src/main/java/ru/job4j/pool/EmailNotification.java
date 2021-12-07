package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
            pool.submit(() -> {
                String subject = "Notification " + user.getName() + " to email " + user.getEmail() + ".";
                String body = "Add a new event to " + user.getName();
                send(subject, body, user.getEmail());
            });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
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
