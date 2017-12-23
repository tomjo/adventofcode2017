package be.tomjo.advent.day5;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static be.tomjo.advent.day5.JumpMaze.part2;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

class JumpMazeTest {

    @Test
    void example1() {
        String input = Stream.of(0, 3, 0, 1, -3).map(String::valueOf).collect(joining("\r\n"));
        assertThat(JumpMaze.part1(input)).isEqualTo(5);
    }

    @Test
    void example2() {
        String input = Stream.of(0, 3, 0, 1, -3).map(String::valueOf).collect(joining("\r\n"));
        assertThat(part2(input)).isEqualTo(10);
    }
}