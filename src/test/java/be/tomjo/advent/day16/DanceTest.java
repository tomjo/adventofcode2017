package be.tomjo.advent.day16;

import org.junit.jupiter.api.Test;

import java.util.List;

import static be.tomjo.advent.day16.Dance.part1;
import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;

class DanceTest {

    @Test
    void example() {
        List<String> moves = of("s1", "x3/4", "pe/b");
        assertThat(part1(moves, 5)).isEqualTo("baedc");
    }
}