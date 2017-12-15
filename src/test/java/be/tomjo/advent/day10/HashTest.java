package be.tomjo.advent.day10;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day10.Hash.knotHash;
import static be.tomjo.advent.day10.Hash.knotHashRound;
import static org.assertj.core.api.Assertions.assertThat;

class HashTest {

    @Test
    void roundExample() {
        int[] values = {0, 1, 2, 3, 4};
        int[] lengths = {3, 4, 1, 5};

        knotHashRound(values, lengths, 0, 0);

        assertThat(values[0]).isEqualTo(3);
        assertThat(values[1]).isEqualTo(4);
        assertThat(values[0]*values[1]).isEqualTo(12);
    }

    @Test
    void sparseToDenseHash() {
        assertThat(Hash.sparseToDenseHash(new int[]{65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22})[0]).isEqualTo(64);
        assertThat(Hash.sparseToDenseHash(new int[]{65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22, 65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22})).containsExactly(64, 64);
    }

    @Test
    void hashExamples() {
        assertThat(knotHash("AoC 2017")).isEqualTo("33efeb34ea91902bb2f59c9920caa6cd");
        assertThat(knotHash("")).isEqualTo("a2582a3a0e66e6e86e3812dcb672a272");
        assertThat(knotHash("1,2,3")).isEqualTo("3efbe78a8d82f29979031a4aa0b16a9d");
        assertThat(knotHash("1,2,4")).isEqualTo("63960835bcdc130f0b66d7ff4f6a5a8e");
    }
}