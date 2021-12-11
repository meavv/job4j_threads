package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchElementTest {

    @Test
    public void whenSearch101() {
        int[] array = {1, 10, 2, 3, 7, 5, 9, 17, 18, 19, 101, 15489, 45, 99, 88, 77};
        var rsl = SearchElement.search(array, 101);
        assertEquals(rsl.intValue(), 10);
    }

    @Test
    public void whenNoValueInArray() {
        int[] array = {1, 10, 2, 3, 7, 5, 9, 17, 18, 19, 101, 15489, 45, 99, 88, 77};
        var rsl = SearchElement.search(array, 999);
        assertEquals(rsl.intValue(), -1);
    }

    @Test
    public void whenSearch1() {
        int[] array = {1, 10, 2, 3, 7, 5, 9, 17, 18, 19, 101, 15489, 45, 99, 88, 77};
        var rsl = SearchElement.search(array, 1);
        assertEquals(rsl.intValue(), 0);
    }
}