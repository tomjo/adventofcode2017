package be.tomjo.advent.day11;

import be.tomjo.advent.IntHolder;

import java.util.function.BinaryOperator;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static be.tomjo.advent.day11.Vector.IDENTITY;
import static com.google.common.primitives.Ints.max;
import static java.util.Arrays.stream;

public class HexGrid {

    public static void main(String[] args) {
        String input = readInput("11.txt");
        System.out.println("Solution 11.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 11.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String inputDirections) {
        Vector reducedDirection = reduceDirections(inputDirections, Vector::add);
        return max(reducedDirection.getX(), reducedDirection.getY(), reducedDirection.getZ());
    }

    public static int part2(String inputDirections) {
        IntHolder furthestStepCounter = new IntHolder(0);
        BinaryOperator<Vector> reduceOperation = (v1, v2) -> {
            Vector result = v1.add(v2);
            int max = max(result.getX(), result.getY(), result.getZ());
            if (max > furthestStepCounter.getValue()) {
                furthestStepCounter.setValue(max);
            }
            return result;
        };
        reduceDirections(inputDirections, reduceOperation);
        return furthestStepCounter.getValue();
    }

    private static Vector reduceDirections(String inputDirections, BinaryOperator<Vector> reduceOperation) {
        return stream(inputDirections.replace("\r\n", "").split(",")).map(Vector::direction).reduce(IDENTITY, reduceOperation);
    }

}
