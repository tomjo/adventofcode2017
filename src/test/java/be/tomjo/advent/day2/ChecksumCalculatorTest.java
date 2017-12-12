package be.tomjo.advent.day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChecksumCalculatorTest {

    private ChecksumCalculator checksumCalculator;

    @BeforeEach
    void setUp() {
        checksumCalculator = new ChecksumCalculator();
    }

    @Test
    void maxMinDifference() {
        int[][] input = {
                {5, 1, 9, 5},
                {7, 5, 3},
                {2, 4, 6, 8}
        };

        int result = checksumCalculator.calculateChecksum(input, ChecksumCalculator::rowMaxMinDifference);

        assertThat(result).isEqualTo(18);
    }

    @Test
    void evenDivision() {
        int[][] input = {
                {5, 9, 2, 8},
                {9, 4, 7, 3},
                {3, 8, 6, 5}
        };

        int result = checksumCalculator.calculateChecksum(input, ChecksumCalculator::rowEvenDivision);

        assertThat(result).isEqualTo(9);
    }
}