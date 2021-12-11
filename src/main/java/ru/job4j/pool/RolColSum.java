package ru.job4j.pool;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {

        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }


        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }


        @Override
        public String toString() {
            return "Sums{" + "rowSum=" + rowSum + ", colSum=" + colSum + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    private static Sums calc(int[][] array, int i) {
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < array[i].length; j++) {
            rowSum += array[i][j];
            colSum += array[j][i];
        }
        return new Sums(rowSum, colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] array = new Sums[(matrix.length)];
        for (int i = 0; i < matrix.length; i++) {
            array[i] = calc(matrix, i);
        }
        System.out.println("Sum " + Arrays.toString(array));
        return array;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] array = new Sums[(matrix.length)];
        for (int a = 0; a < array.length; a++) {
            array[a] = run(matrix, a).get();
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static CompletableFuture<Sums> run(int[][] array, int i) {
        return CompletableFuture.supplyAsync(() -> calc(array, i));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] twoDimArray1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.sum(twoDimArray1);
        RolColSum.asyncSum(twoDimArray1);
    }

}