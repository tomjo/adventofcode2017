package be.tomjo.advent.day15;

import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class GeneratorTest {

    @Test
    void examples() {
        Generator generatorA = new Generator(65, 16807, MAX_VALUE);
        Generator generatorB = new Generator(8921, 48271, MAX_VALUE);

        assertThat(generatorA.generate()).isEqualTo(1092455);
        assertThat(generatorB.generate()).isEqualTo(430625591);
        assertThat(generatorA.generate()).isEqualTo(1181022009);
        assertThat(generatorB.generate()).isEqualTo(1233683848);
        assertThat(generatorA.generate()).isEqualTo(245556042);
        assertThat(generatorB.generate()).isEqualTo(1431495498);
        assertThat(generatorA.generate()).isEqualTo(1744312007);
        assertThat(generatorB.generate()).isEqualTo(137874439);
        assertThat(generatorA.generate()).isEqualTo(1352636452);
        assertThat(generatorB.generate()).isEqualTo(285222916);
    }
}