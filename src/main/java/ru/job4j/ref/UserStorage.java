package ru.job4j.ref;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {



    @GuardedBy("this")
    private final HashMap<Integer, User> userList = new HashMap<>();

    public synchronized boolean add(User user) {
        if (!userList.containsKey(user.getId())) {
            userList.put(user.getId(), user);
            return userList.containsValue(user);
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (userList.containsKey(user.getId())) {
            userList.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (userList.containsValue(user)) {
            userList.remove(user.getId());
            return true;
        }
        return false;
    }

    public  synchronized boolean transfer(int fromId, int toId, int amount) {
        User userTo = userList.get(toId);
        User userFrom = userList.get(fromId);
        UserStorage userStorage = new UserStorage();
        if (userTo != null && userFrom != null && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userStorage.update(userFrom);
            userTo.setAmount(userTo.getAmount() + amount);
            return true;
        }
        return false;
    }

    public synchronized HashMap<Integer, User> getUserList() {
        return new HashMap<>(userList);
    }

}
