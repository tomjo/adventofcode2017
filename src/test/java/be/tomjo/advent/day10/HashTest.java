package be.tomjo.advent.day10;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day10.Hash.knotHash;
import static org.assertj.core.api.Assertions.assertThat;

class HashTest {

    @Test
    void example() {
        int[] values = {0, 1, 2, 3, 4};
        int[] lengths = {3, 4, 1, 5};

        int result = knotHash(values, lengths);

        assertThat(result).isEqualTo(12);
    }
}