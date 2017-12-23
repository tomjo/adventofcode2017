package be.tomjo.advent.day17;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;

public class SpinLock {

    public static void main(String[] args) {
        String input = readInput("17.txt");
        System.out.println("Solution 17.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 17.2: " + benchmark(() -> part2(input)));
    }

    public static int part2(String input) {
        int startSkipSize = parseInt(input);
        int currentPosition = 0;
        int valueAfterZero = 0;
        int valueSkipSize = startSkipSize;
        for (int value = 1; value < 50000001; value += valueSkipSize) {
            int positionSkipSize = valueSkipSize * (startSkipSize + 1) - 1;
            currentPosition = (currentPosition + positionSkipSize) % value + 1;
            if (currentPosition == 1) {
                valueAfterZero = value;
            }
            valueSkipSize = ((value - currentPosition) / startSkipSize) + 1;
        }
        return valueAfterZero;
    }

    public static int part1(String input) {
        int skipSize = parseInt(input);
        int[] buffer = new int[2018];
        int size = 1;
        int currentPosition = 0;
        for (int value = 1; value < 2018; value++) {
            currentPosition = (currentPosition + skipSize) % size + 1;
            size++;
            insert(buffer, size, currentPosition, value);
        }
        return buffer[currentPosition + 1];
    }


    private static void insert(int[] buffer, int size, int position, int value) {
        System.arraycopy(buffer, position, buffer, position + 1, size - position - 1);
        buffer[position] = value;
    }
}
