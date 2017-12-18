package be.tomjo.advent.day17;

import static be.tomjo.advent.Util.indexOf;

public class SpinLock {

    public static void main(String[] args) {
        System.out.println("Solution 17.1: " + part1(371));
    }

    public static int part1(int input) {
        int[] buffer = new int[2018];
        int size = 1;
        int currentPosition = 0;
        for (int value = 1; value < 2018; value++) {
            currentPosition = (currentPosition + input) % size + 1;
            size++;
            insert(buffer, size, currentPosition, value);
        }
        return buffer[indexOf(buffer, 2017) + 1];
    }


    public static void insert(int[] buffer, int size, int position, int value) {
        System.arraycopy(buffer, position, buffer, position + 1, size - position - 1);
        buffer[position] = value;
    }
}
