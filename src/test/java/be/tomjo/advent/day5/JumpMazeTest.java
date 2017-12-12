package be.tomjo.advent.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class JumpMazeTest {

    @Test
    void example1() {
        List<Integer> offsets = asList(0, 3, 0, 1, -3);
        assertThat(JumpMaze.escapeJumpOffsetMaze(offsets)).isEqualTo(5);
    }

    @Test
    void example2() {
        List<Integer> offsets = asList(0, 3, 0, 1, -3);
        assertThat(JumpMaze.escapeJumpOffsetMaze2(offsets)).isEqualTo(10);
    }
}