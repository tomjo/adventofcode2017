package be.tomjo.advent.day5;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

import static be.tomjo.advent.Util.readInput;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class JumpMaze {

    public static void main(String[] args) throws IOException {
        String input = readInput("5.txt");

        List<Integer> jumpOffsets = Arrays.stream(input.split("\r\n")).mapToInt(Integer::parseInt).boxed().collect(toList());

        System.out.println("Solution 5.1: " + escapeJumpOffsetMaze(newArrayList(jumpOffsets)));
        System.out.println("Solution 5.2: " + escapeJumpOffsetMaze2(newArrayList(jumpOffsets)));
    }

    public static int escapeJumpOffsetMaze(List<Integer> jumpOffsets) {
        return escape(jumpOffsets, jumpOffset -> jumpOffset + 1);
    }

    public static int escapeJumpOffsetMaze2(List<Integer> jumpOffsets) {
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
