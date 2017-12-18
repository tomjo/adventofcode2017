package be.tomjo.advent.day15;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day15.Generators.getMatchingCount;
import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class GeneratorsTest {

    @Test
    void example() {
        Generator generatorA = new Generator(65, 16807, MAX_VALUE);
        Generator generatorB = new Generator(8921, 48271, MAX_VALUE);

        assertThat(getMatchingCount(generatorA, generatorB)).isEqualTo(588);
    }
}