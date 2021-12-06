package ru.job4j.cache;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {



    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("Qwe");
        assertTrue(cache.add(base));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("Qwe");
        Base newBase = new Base(1, 1);
        newBase.setName("Abs");
        cache.add(base);
        assertTrue((cache.update(newBase)));
        assertEquals(cache.get(1).getName(), "Abs");
    }

    @Test (expected = OptimisticException.class)
    public void updateWhenAnotherVersion() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("Qwe");
        Base newBase = new Base(1, 2);
        newBase.setName("Abs");
        cache.add(base);
        cache.update(newBase);
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        base.setName("Qwe");
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(1));
    }
}