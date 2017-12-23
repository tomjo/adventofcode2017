package be.tomjo.advent.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static be.tomjo.advent.day16.Dance.part1;
import static be.tomjo.advent.day16.Dance.performDance;
import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;

class DanceTest {

    @Test
    void example2() {
        List<String> moves = of("s1", "x3/4", "pe/b");
        assertThat(performDance(moves, 5, 1)).isEqualTo("baedc");
        assertThat(performDance(moves, 5, 2)).isEqualTo("ceadb");
        assertThat(performDance(moves, 5, 1000000000)).isEqualTo("abcde");
    }
}