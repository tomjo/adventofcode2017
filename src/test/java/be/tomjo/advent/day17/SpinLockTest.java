package be.tomjo.advent.day17;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day17.SpinLock.part1;
import static org.assertj.core.api.Assertions.assertThat;

class SpinLockTest {

    @Test
    void example() {
        assertThat(part1("3")).isEqualTo(638);
        assertThat(part1("371")).isEqualTo(1311);
    }
}