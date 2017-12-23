package be.tomjo.advent.day11;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day11.HexGrid.part1;
import static org.assertj.core.api.Assertions.assertThat;

class HexGridTest {

    @Test
    void examples() {
        assertThat(part1("ne,ne,ne")).isEqualTo(3);
        assertThat(part1("ne,ne,sw,sw")).isEqualTo(0);
        assertThat(part1("ne,ne,s,s")).isEqualTo(2);
        assertThat(part1("se,sw,se,sw,sw")).isEqualTo(3);
    }
}