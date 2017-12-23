package be.tomjo.advent.day14;

import be.tomjo.advent.day10.Hash;

import java.math.BigInteger;
import java.util.List;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class DiskDefragmenter {

    public static void main(String[] args) {
        String input = readInput("14.txt");
        System.out.println("Solution 14.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 14.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input) {
        return range(0, 128)
                .mapToObj(i -> input + "-" + i)
                .map(Hash::knotHash)
                .map(DiskDefragmenter::knotHashToBits)
                .flatMapToInt(CharSequence::chars)
                .map(i -> i - '0')
                .sum();
    }

    public static int part2(String input) {
        List<int[]> rows = range(0, 128)
                .mapToObj(i -> input + "-" + i)
                .map(Hash::knotHash)
                .map(DiskDefragmenter::knotHashToBits)
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .collect(toList());
        int[][] grid = createGrid(rows);
        return countRegions(grid);
    }

    private static int[][] createGrid(List<int[]> rows) {
        int[][] grid = new int[128][];
        for (int i = 0; i < rows.size(); i++) {
            grid[i] = rows.get(i);
        }
        return grid;
    }

    private static int countRegions(int[][] grid) {
        int marker = '1';
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] == 1) {
                    fillRegion(grid, x, y, marker++);
                }
            }
        }
        return marker - '1';
    }

    private static void fillRegion(int[][] grid, int x, int y, int marker) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            grid[x][y] = marker;
            fillRegion(grid, x, y - 1, marker);
            fillRegion(grid, x, y + 1, marker);
            fillRegion(grid, x - 1, y, marker);
            fillRegion(grid, x + 1, y, marker);
        }
    }

    public static String knotHashToBits(String knotHash) {
        return knotHash.chars()
                .map(DiskDefragmenter::hexCharToIntValue)
                .mapToObj(Integer::toBinaryString)
                .map(DiskDefragmenter::ensure4Digits)
                .collect(joining());
    }

    private static int hexCharToIntValue(int c) {
        return parseInt("" + (char) c, 16);
    }

    private static String ensure4Digits(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        while (stringBuilder.length() < 4) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }
}
