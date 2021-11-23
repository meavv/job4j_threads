package ru.job4j.ref;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {


    @GuardedBy("this")
    private final HashMap<Integer, User> userList = new HashMap<>();

    public synchronized boolean add(User user) {
        return userList.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return userList.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userTo = userList.get(toId);
        User userFrom = userList.get(fromId);
        if (userTo != null && userFrom != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            return true;
        }
        return false;
    }

    public synchronized HashMap<Integer, User> getUserList() {
        return new HashMap<>(userList);
    }

}
