package be.tomjo.advent.day15;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day15.Generators.getMatchingCount;
import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class GeneratorsTest {

    @Test
    void getMatchingCountExample() {
        Generator generatorA = new Generator(65, 16807, MAX_VALUE);
        Generator generatorB = new Generator(8921, 48271, MAX_VALUE);

        assertThat(getMatchingCount(generatorA, generatorB, 40000000)).isEqualTo(588);
    }

    @Test
    void getMatchingCountWithCriteriaExample() {
        Generator generatorA = new Generator(65, 16807, MAX_VALUE, Generators::isMultipleOf4);
        Generator generatorB = new Generator(8921, 48271, MAX_VALUE, Generators::isMultipleOf8);

        assertThat(getMatchingCount(generatorA, generatorB, 5000000)).isEqualTo(309);
    }

    @Test
    void generateMatchingCriteriaExamples() {
        Generator generatorA = new Generator(65, 16807, MAX_VALUE, Generators::isMultipleOf4);
        Generator generatorB = new Generator(8921, 48271, MAX_VALUE, Generators::isMultipleOf8);

        assertThat(generatorA.generate()).isEqualTo(1352636452);
        assertThat(generatorA.generate()).isEqualTo(1992081072);
        assertThat(generatorA.generate()).isEqualTo(530830436);
        assertThat(generatorA.generate()).isEqualTo(1980017072);
        assertThat(generatorA.generate()).isEqualTo(740335192);

        assertThat(generatorB.generate()).isEqualTo(1233683848);
        assertThat(generatorB.generate()).isEqualTo(862516352);
        assertThat(generatorB.generate()).isEqualTo(1159784568);
        assertThat(generatorB.generate()).isEqualTo(1616057672);
        assertThat(generatorB.generate()).isEqualTo(412269392);
    }
}