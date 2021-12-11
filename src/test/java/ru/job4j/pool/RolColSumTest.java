package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.pool.RolColSum;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] twoDimArray1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var rsl = RolColSum.sum(twoDimArray1);
        RolColSum.Sums sums1 = new RolColSum.Sums(6, 12);
        RolColSum.Sums sums2 = new RolColSum.Sums(15, 15);
        RolColSum.Sums sums3 = new RolColSum.Sums(24, 18);
        assertEquals(rsl[0], sums1);
        assertEquals(rsl[1], sums2);
        assertEquals(rsl[2], sums3);
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] twoDimArray1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var rsl = RolColSum.asyncSum(twoDimArray1);
        RolColSum.Sums sums1 = new RolColSum.Sums(6, 12);
        RolColSum.Sums sums2 = new RolColSum.Sums(15, 15);
        RolColSum.Sums sums3 = new RolColSum.Sums(24, 18);
        assertEquals(rsl[0], sums1);
        assertEquals(rsl[1], sums2);
        assertEquals(rsl[2], sums3);
    }
}