package be.tomjo.advent.day2;

import com.google.common.primitives.Ints;

import java.io.IOException;
import java.util.List;
import java.util.function.ToIntFunction;

import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;

public class ChecksumCalculator {

    public static void main(String[] args) throws IOException {
        ChecksumCalculator checksumCalculator = new ChecksumCalculator();
        String input = readInput("2.txt");
        int[][] spreadsheet = toSpreadsheet(input);

        System.out.println("Solution 2.1: " + checksumCalculator.calculateChecksum(spreadsheet, ChecksumCalculator::rowMaxMinDifference));
        System.out.println("Solution 2.2: " + checksumCalculator.calculateChecksum(spreadsheet, ChecksumCalculator::rowEvenDivision));
    }

    public int calculateChecksum(int[][] spreadsheet, ToIntFunction<int[]> rowChecksum) {
        return stream(spreadsheet)
                .mapToInt(rowChecksum)
                .sum();
    }

    public static int rowMaxMinDifference(int[] row) {
        return Ints.max(row) - Ints.min(row);
    }

    public static int rowEvenDivision(int[] row) {
        List<Integer> numbers = Ints.asList(row);
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                int max = Ints.max(numbers.get(i), numbers.get(j));
                int min = Ints.min(numbers.get(i), numbers.get(j));
                if (areEvenlyDivisible(max, min)) {
                    return max / min;
                }
            }
        }
        throw new IllegalStateException();
    }

    private static boolean areEvenlyDivisible(int n1, int n2) {
        return (n1 / n2) * n2 == n1;
    }

    private static int[][] toSpreadsheet(String input) {
        String[] rows = input.split("\r\n");
        int[][] spreadsheet = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            spreadsheet[i] = stream(row.split("\t"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return spreadsheet;
    }
}
