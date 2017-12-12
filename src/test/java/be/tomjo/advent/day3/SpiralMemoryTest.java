package be.tomjo.advent.day3;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day3.Point.point;
import static org.assertj.core.api.Assertions.assertThat;

class SpiralMemoryTest {

    @Test
    void getValue_returnsValueOfNodeAtPosition() {
        SpiralMemory spiralMemory = new SpiralMemory();

        spiralMemory.addNode(new MemoryNode(1, 1));

        int result = spiralMemory.getValue(point(0, 0));

        assertThat(result).isEqualTo(1);
    }

    @Test
    void getValue_noNode_returns0() {
        SpiralMemory spiralMemory = new SpiralMemory();

        int result = spiralMemory.getValue(point(0, 0));

        assertThat(result).isEqualTo(0);
    }
}