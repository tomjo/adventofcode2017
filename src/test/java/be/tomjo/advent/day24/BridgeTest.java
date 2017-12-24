package be.tomjo.advent.day24;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day24.Bridge.part1;
import static be.tomjo.advent.day24.Bridge.part2;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

class BridgeTest {

    @Test
    void example() {
        String input = stream(new String[]{
                "0/2",
                "2/2",
                "2/3",
                "3/4",
                "3/5",
                "0/1",
                "10/1",
                "9/10"})
                .collect(joining("\r\n"));
        assertThat(part1(input)).isEqualTo(31);
    }

    @Test
    void example2() {
        String input = stream(new String[]{
                "0/2",
                "2/2",
                "2/3",
                "3/4",
                "3/5",
                "0/1",
                "10/1",
                "9/10"})
                .collect(joining("\r\n"));
        assertThat(part2(input)).isEqualTo(19);
    }
}