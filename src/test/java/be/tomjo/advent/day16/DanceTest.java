package be.tomjo.advent.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static be.tomjo.advent.day16.Dance.part1;
import static be.tomjo.advent.day16.Dance.part2;
import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;

class DanceTest {

    @Test
    void example() {
        List<String> moves = of("s1", "x3/4", "pe/b");
        assertThat(part1(moves, 5)).isEqualTo("baedc");
    }

    @Test
    void example2() {
        List<String> moves = of("s1", "x3/4", "pe/b");
        assertThat(part2(moves, 5, 1)).isEqualTo("baedc");
        assertThat(part2(moves, 5, 2)).isEqualTo("ceadb");
        assertThat(part2(moves, 5, 1000000000)).isEqualTo("abcde");
    }
}