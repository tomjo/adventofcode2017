package be.tomjo.advent.day11;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day11.HexGrid.fewestStepsToEndDestination;
import static org.assertj.core.api.Assertions.assertThat;

class HexGridTest {

    @Test
    void examples() {
        assertThat(fewestStepsToEndDestination("ne,ne,ne")).isEqualTo(3);
        assertThat(fewestStepsToEndDestination("ne,ne,sw,sw")).isEqualTo(0);
        assertThat(fewestStepsToEndDestination("ne,ne,s,s")).isEqualTo(2);
        assertThat(fewestStepsToEndDestination("se,sw,se,sw,sw")).isEqualTo(3);
    }
}