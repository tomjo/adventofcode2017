package be.tomjo.advent.day15;

import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.IntStream.range;

public class Generators {

    public static void main(String[] args) {
        String[] input = readInput("15.txt").split("\r\n");

        System.out.println("Solution 15.1: " + part1(input));
        System.out.println("Solution 15.2: " + part2(input));
    }

    public static int part1(String[] input){
        Generator generatorA = new Generator(getStartValue(input[0]), 16807, MAX_VALUE);
        Generator generatorB = new Generator(getStartValue(input[1]), 48271, MAX_VALUE);
        return getMatchingCount(generatorA, generatorB,40000000);
    }

    public static int part2(String[] input){
        Generator generatorA = new Generator(getStartValue(input[0]), 16807, MAX_VALUE, Generators::isMultipleOf4);
        Generator generatorB = new Generator(getStartValue(input[1]), 48271, MAX_VALUE, Generators::isMultipleOf8);
        return getMatchingCount(generatorA, generatorB,5000000);
    }

    public static int getMatchingCount(Generator generatorA, Generator generatorB, int amount) {
        int[] lowestBitsA = generateNTimes16LowestBits(generatorA, amount);
        int[] lowestBitsB = generateNTimes16LowestBits(generatorB, amount);
        return countMatches(lowestBitsA, lowestBitsB);
    }

    private static int[] generateNTimes16LowestBits(Generator generator, int amount) {
        return range(0, amount)
                .mapToLong(i -> generator.generate())
                .mapToInt(Generators::takeLowest16Bits)
                .toArray();
    }

    private static int takeLowest16Bits(long l) {
        return (int) (l & 0xffff);
    }

    private static int countMatches(int[] lowestBitsA, int[] lowestBitsB) {
        return (int) range(0, lowestBitsA.length).filter(i -> lowestBitsA[i] == lowestBitsB[i]).count();
    }

    public static boolean isMultipleOf4(long l) {
        return l % 4 == 0;
    }

    public static boolean isMultipleOf8(long l) {
        return l % 8 == 0;
    }

    private static int getStartValue(String record) {
        String[] parts = record.split(" ");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
