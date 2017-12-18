package be.tomjo.advent.day18;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day18.Duet.part1;
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

        assertThat(part1(instructions)).isEqualTo(4);
    }
}