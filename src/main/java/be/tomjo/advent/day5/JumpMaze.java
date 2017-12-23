package be.tomjo.advent.day5;

import java.util.List;
import java.util.function.IntFunction;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class JumpMaze {

    public static void main(String[] args) {
        String input = readInput("5.txt");
        System.out.println("Solution 5.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 5.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input){
        List<Integer> jumpOffsets = toJumpOffsets(input);
        return escape(jumpOffsets, jumpOffset -> jumpOffset + 1);
    }

    private static List<Integer> toJumpOffsets(String input) {
        return stream(input.split("\r\n"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
    }

    public static int part2(String input){
        List<Integer> jumpOffsets = toJumpOffsets(input);
        return escape(jumpOffsets, jumpOffset -> jumpOffset + (jumpOffset >= 3 ? -1 : 1));
    }

    private static int escape(List<Integer> jumpOffsets, IntFunction<Integer> offsetManipulation) {
        int nextInstruction = 0;
        int steps = 0;
        while (nextInstruction >= 0 && nextInstruction < jumpOffsets.size()) {
            int jumpOffset = jumpOffsets.get(nextInstruction);
            jumpOffsets.set(nextInstruction, offsetManipulation.apply(jumpOffset));
            nextInstruction += jumpOffset;
            steps++;
        }
        return steps;
    }
}
