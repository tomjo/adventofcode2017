package be.tomjo.advent.day3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    private Day3 day3;

    @BeforeEach
    void setUp() {
        day3 = new Day3();
    }

    @Test
    void manhattanDistance_examples() {
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(1)).isEqualTo(0);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(2)).isEqualTo(1);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(3)).isEqualTo(2);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(4)).isEqualTo(1);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(5)).isEqualTo(2);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(6)).isEqualTo(1);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(9)).isEqualTo(2);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(12)).isEqualTo(3);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(14)).isEqualTo(3);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(17)).isEqualTo(4);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(18)).isEqualTo(3);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(19)).isEqualTo(2);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(20)).isEqualTo(3);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(22)).isEqualTo(3);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(23)).isEqualTo(2);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(25)).isEqualTo(4);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(1024)).isEqualTo(31);
        assertThat(day3.calculateManhattanDistanceFromNodeIdToAccessPort(361527)).isEqualTo(326);
    }
}