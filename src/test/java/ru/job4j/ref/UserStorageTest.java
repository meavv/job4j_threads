package ru.job4j.ref;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void add() {
        UserStorage userStorage = new UserStorage();
        assertTrue(userStorage.add(new User(1, 100)));
        assertTrue(userStorage.add(new User(2, 100)));
        assertTrue(userStorage.add(new User(3, 100)));
        assertFalse(userStorage.add(new User(1, 200)));
    }

    @Test
    public void update() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 100));
        assertTrue(userStorage.update(new User(1, 200)));
        assertFalse(userStorage.update(new User(2, 200)));
    }

    @Test
    public void delete() {
        UserStorage userStorage = new UserStorage();
        User user = new User(1, 100);
        userStorage.add(user);
        assertTrue(userStorage.delete(user));
        assertFalse(userStorage.getUserList().containsKey(user.getId()));
    }

    @Test
    public void transfer() {
        UserStorage userStorage = new UserStorage();
        User userFrom = new User(1, 100);
        User userTo = new User(2, 200);
        userStorage.add(userFrom);
        userStorage.add(userTo);
        assertTrue(userStorage.transfer(userFrom.getId(), userTo.getId(), 50));
        assertEquals(userFrom.getAmount(), 50);
        assertEquals(userTo.getAmount(), 250);
    }

    @Test
    public void transferWhenUserNullPointer() {
        UserStorage userStorage = new UserStorage();
        User userFrom = new User(1, 100);
        User userTo = new User(2, 200);
        userStorage.add(userFrom);
        userStorage.add(userTo);
        assertFalse(userStorage.transfer(1, 3, 50));
    }
}