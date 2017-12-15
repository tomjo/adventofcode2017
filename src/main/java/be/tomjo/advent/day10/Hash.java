package be.tomjo.advent.day10;

import be.tomjo.advent.Util;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class Hash {

    public static void main(String[] args) {
        int[] values = range(0, 256).toArray();
        int[] lengths = Arrays.stream(Util.readInput("10.txt").split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Solution 10.1: " + knotHash(values, lengths));
    }

    public static int knotHash(int[] values, int[] lengths) {
        int currentPosition = 0;
        int skipSize = 0;
        for (int length : lengths) {
            reverse(values, currentPosition, currentPosition+length);
            currentPosition += length + skipSize;
            skipSize++;
        }
        return values[0]*values[1];
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

}
