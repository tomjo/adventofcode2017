package be.tomjo.advent.day18;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static be.tomjo.advent.day18.Duet.part1;
import static be.tomjo.advent.day18.Duet.part2;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

class DuetTest {

    @Test
    void example() {
        String[] instructions = {
                "set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2"
        };

        assertThat(part1(stream(instructions).collect(joining("\r\n")))).isEqualTo(4);
    }

    @Test
    void example2() {
        String[] instructions = {
                "snd 1",
                "snd 2",
                "snd p",
                "rcv a",
                "rcv b",
                "rcv c",
                "rcv d"
        };
        assertThat(part2(stream(instructions).collect(joining("\r\n")))).isEqualTo(3);
    }
}