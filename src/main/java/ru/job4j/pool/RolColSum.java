package ru.job4j.pool;

import java.util.Arrays;
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
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] array = new Sums[(matrix.length)];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            array[i] = new Sums(rowSum, colSum);
        }
        System.out.println("Standart " + Arrays.toString(array));
        return array;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] array = new Sums[(matrix.length)];
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(run(matrix));
        }
        return array;
    }

    public static CompletableFuture<Sums> run(int[][] array) {
        return CompletableFuture.supplyAsync(() -> {
            var rsl = new Sums(0, 0);
            for (int i = 0; i < array.length; i++) {
                int rowSum = 0;
                int colSum = 0;
                for (int j = 0; j < array[i].length; j++) {
                    rowSum += array[i][j];
                    colSum += array[j][i];
                }
                rsl = new Sums(rowSum, colSum);
                return rsl;
            }
            return rsl;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] twoDimArray1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.asyncSum(twoDimArray1);
    }

}