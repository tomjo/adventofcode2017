package be.tomjo.advent.day6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReallocationRoutineTest {

    private ReallocationRoutine reallocationRoutine;

    @BeforeEach
    void setUp() {
        reallocationRoutine = new ReallocationRoutine();
    }

    @Test
    void example1() {
        int[] memoryBanks = {0, 2, 7, 0};

        reallocationRoutine.reallocate(memoryBanks);

        assertThat(memoryBanks).containsExactly(2, 4, 1, 2);
    }

    @Test
    void example2() {
        int[] memoryBanks = {2, 4, 1, 2};

        reallocationRoutine.reallocate(memoryBanks);

        assertThat(memoryBanks).containsExactly(3, 1, 2, 3);
    }

    @Test
    void example3() {
        int[] memoryBanks = {3, 1, 2, 3};

        reallocationRoutine.reallocate(memoryBanks);

        assertThat(memoryBanks).containsExactly(0, 2, 3, 4);
    }

    @Test
    void example4() {
        int[] memoryBanks = {0, 2, 3, 4};

        reallocationRoutine.reallocate(memoryBanks);

        assertThat(memoryBanks).containsExactly(1, 3, 4, 1);
    }

    @Test
    void example5() {
        int[] memoryBanks = {1, 3, 4, 1};

        reallocationRoutine.reallocate(memoryBanks);

        assertThat(memoryBanks).containsExactly(2, 4, 1, 2);
    }

    @Test
    void reallocateUntilCycle() {
        int[] memoryBanks = {0, 2, 7, 0};

        int result = reallocationRoutine.reallocateUntilCycleFound(memoryBanks);

        assertThat(result).isEqualTo(5);
        assertThat(reallocationRoutine.getCycleCount()).isEqualTo(4);
    }
}