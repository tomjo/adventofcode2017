package be.tomjo.advent.day22;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VirusCarrierTest {

    @Test
    void example() {
        String map = "..#\r\n#..\r\n...";

        VirusCarrier virusCarrier = new VirusCarrier(map);

        for (int i = 0; i < 7; i++) {
            virusCarrier.burst();
        }

        assertThat(virusCarrier.getInfectionCount()).isEqualTo(5);

        for (int i = 0; i < 63; i++) {
            virusCarrier.burst();
        }

        assertThat(virusCarrier.getInfectionCount()).isEqualTo(41);

        for (int i = 0; i < 9930; i++) {
            virusCarrier.burst();
        }

        assertThat(virusCarrier.getInfectionCount()).isEqualTo(5587);
    }

    @Test
    void example2() {
        String map = "..#\r\n#..\r\n...";

        VirusCarrier virusCarrier = new VirusCarrier(map);

        for (int i = 0; i < 100; i++) {
            virusCarrier.burst2();
        }

        assertThat(virusCarrier.getInfectionCount()).isEqualTo(26);
    }

    @Test
    void example2full() {
        String map = "..#\r\n#..\r\n...";

        assertThat(VirusCarrier.part2(map)).isEqualTo(2511944);
    }
}