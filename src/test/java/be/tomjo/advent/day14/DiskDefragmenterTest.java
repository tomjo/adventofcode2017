package be.tomjo.advent.day14;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day14.DiskDefragmenter.knotHashToBits;
import static be.tomjo.advent.day14.DiskDefragmenter.part1;
import static be.tomjo.advent.day14.DiskDefragmenter.part2;
import static org.assertj.core.api.Assertions.assertThat;

class DiskDefragmenterTest {

    @Test
    void knotHashToBitsExample() {
        assertThat(knotHashToBits("a0c2017")).isEqualTo("1010000011000010000000010111");
    }

    @Test
    void example1() {
        assertThat(part1("flqrgnkx")).isEqualTo(8108);
    }

    @Test
    void example2() {
        assertThat(part2("flqrgnkx")).isEqualTo(1242);
    }
}