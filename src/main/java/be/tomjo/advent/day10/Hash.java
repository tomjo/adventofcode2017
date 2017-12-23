package be.tomjo.advent.day10;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.concat;
import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class Hash {

    private static final int[] SUFFIX = {17, 31, 73, 47, 23};

    public static void main(String[] args) {
        String input = readInput("10.txt");
        System.out.println("Solution 10.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 10.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input) {
        int[] values = range(0, 256).toArray();
        knotHashRound(values, getLengthsFromInput1(input), 0, 0);
        return values[0] * values[1];
    }

    public static String part2(String input) {
        return knotHash(input);
    }

    public static String knotHash(String input) {
        return hash(input, 64);
    }

    public static String hash(String input, int rounds) {
        int currentPosition = 0;
        int skipSize = 0;
        int[] values = range(0, 256).toArray();
        for (int i = 0; i < rounds; i++) {
            RoundOutput output = knotHashRound(values, getLengthsFromInput2(input), currentPosition, skipSize);
            currentPosition = output.getCurrentPosition();
            skipSize = output.getSkipSize();
        }
        int[] denseHash = sparseToDenseHash(values);
        return stream(denseHash).mapToObj(Integer::toHexString).map(Hash::ensureTwoSymbols).collect(joining());
    }

    public static int[] sparseToDenseHash(int[] values) {
        int[] denseHash = new int[values.length / 16];
        int currentBlock = 0;
        int blockHash = 0;
        for (int i = 0; i < values.length; i++) {
            if (i % 16 == 0) {
                if (i != 0) {
                    denseHash[currentBlock++] = blockHash;
                }
                blockHash = values[i];
            } else {
                blockHash ^= values[i];
            }
        }
        denseHash[currentBlock] = blockHash;
        return denseHash;
    }

    public static RoundOutput knotHashRound(int[] values, int[] lengths, int currentPosition, int skipSize) {
        for (int length : lengths) {
            reverse(values, currentPosition, currentPosition + length);
            currentPosition += length + skipSize;
            skipSize++;
        }
        return new RoundOutput(currentPosition, skipSize);
    }

    private static void reverse(int[] values, int start, int end) {
        List<Integer> subListToReverse = addElementsFromCircularArrayToList(values, start, end);
        Collections.reverse(subListToReverse);
        mapSubListBackToCircularArray(values, start, subListToReverse);
    }

    private static List<Integer> addElementsFromCircularArrayToList(int[] values, int start, int end) {
        return range(start, end)
                .mapToObj(i -> values[i % values.length])
                .collect(Collectors.toList());
    }

    private static void mapSubListBackToCircularArray(int[] values, int start, List<Integer> subListToReverse) {
        for (int i = 0; i < subListToReverse.size(); i++) {
            values[(start + i) % values.length] = subListToReverse.get(i);
        }
    }

    private static String ensureTwoSymbols(String s) {
        return s.length() < 2 ? "0" + s : s;
    }

    private static int[] getLengthsFromInput1(String input) {
        return stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    private static int[] getLengthsFromInput2(String input) {
        return concat(input.chars().toArray(), SUFFIX);
    }

    static class RoundOutput {

        private final int currentPosition;
        private final int skipSize;

        RoundOutput(int currentPosition, int skipSize) {
            this.currentPosition = currentPosition;
            this.skipSize = skipSize;
        }

        public int getCurrentPosition() {
            return currentPosition;
        }

        public int getSkipSize() {
            return skipSize;
        }

    }

}


















