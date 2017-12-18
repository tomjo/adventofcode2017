package be.tomjo.advent.day15;

import java.util.stream.LongStream;

import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.IntStream.range;

public class Generators {

    public static void main(String[] args) {
        String[] input = readInput("15.txt").split("\r\n");
        Generator generatorA = new Generator(getStartValue(input[0]), 16807, MAX_VALUE);
        Generator generatorB = new Generator(getStartValue(input[1]), 48271, MAX_VALUE);
        System.out.println("Solution 15.1: "+ getMatchingCount(generatorA, generatorB));
    }

    public static int getMatchingCount(Generator generatorA, Generator generatorB) {
        int[] lowestBitsA = generate(generatorA).mapToInt(l -> (int) (l & 0xffff)).toArray();
        int[] lowestBitsB = generate(generatorB).mapToInt(l -> (int) (l & 0xffff)).toArray();
        return (int) range(0, lowestBitsA.length).filter(i -> lowestBitsA[i] == lowestBitsB[i]).count();
    }

    private static LongStream generate(Generator generatorA) {
        return range(0, 40000000).mapToLong(i -> generatorA.generate());
    }

    private static int getStartValue(String record) {
        String[] parts = record.split(" ");
        return Integer.parseInt(parts[parts.length-1]);
    }
}
