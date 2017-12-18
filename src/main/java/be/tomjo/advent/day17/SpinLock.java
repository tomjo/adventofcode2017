package be.tomjo.advent.day17;

public class SpinLock {

    public static void main(String[] args) {
        System.out.println("Solution 17.1: " + part1(371));
        System.out.println("Solution 17.2: " + part2(371));
    }

    public static int part2(int input) {
        int currentPosition = 0;
        int valueAfterZero = 0;
        for (int value = 1; value < 50000001; value++) {
            currentPosition = (currentPosition + input) % value + 1;
            if(currentPosition == 1){
                valueAfterZero = value;
            }
        }
        return valueAfterZero;
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
        return buffer[currentPosition + 1];
    }


    private static void insert(int[] buffer, int size, int position, int value) {
        System.arraycopy(buffer, position, buffer, position + 1, size - position - 1);
        buffer[position] = value;
    }
}
