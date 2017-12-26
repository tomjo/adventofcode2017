package be.tomjo.advent.day25;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

class Day25Test {

    @Test
    void example() {
        String input = stream(new String[]{
                "Begin in state A.",
                "Perform a diagnostic checksum after 6 steps.",

                "In state A:",
                " If the current value is 0:",
                " - Write the value 1.",
                "         - Move one slot to the right.",
                "         - Continue with state B.",
                "        If the current value is 1:",
                " - Write the value 0.",
                "        - Move one slot to the left.",
                "       - Continue with state B.",

                "      In state B:",
                "If the current value is 0:",
                "- Write the value 1.",
                "       - Move one slot to the left.",
                "      - Continue with state A.",
                "     If the current value is 1:",
                "- Write the value 1.",
                "       - Move one slot to the right.",
                "      - Continue with state A.",})
                .collect(joining("\r\n"));

        int result = Day25.part1(input);

        assertThat(result).isEqualTo(3);
    }
}