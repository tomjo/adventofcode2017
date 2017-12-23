package be.tomjo.advent.day21;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static be.tomjo.advent.day21.Art.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArtTest {

    @Test
    void horizontalFlip3x3() {
        assertThat(horizontalFlip("010/001/111")).isEqualTo("010/100/111");
    }

    @Test
    void horizontalFlip1x3() {
        assertThat(horizontalFlip("001")).isEqualTo("100");
        assertThat(horizontalFlip("011")).isEqualTo("110");
    }

    @Test
    void rotate90CWExamples() {
        assertThat(rotate90CW("10/01")).isEqualTo("01/10");
        assertThat(rotate90CW("100/010/001")).isEqualTo("001/010/100");
        assertThat(rotate90CW(rotate90CW("100/010/001"))).isEqualTo("100/010/001");
        assertThat(rotate90CW(rotate90CW(rotate90CW("100/010/001")))).isEqualTo("001/010/100");
    }

    @Test
    void subdivide2Example() {
        assertThat(subdivide("01/23")).isEqualTo(new String[][]{{"01/23"}});
        assertThat(subdivide("0123/4567/89ab/cdef")).isEqualTo(new String[][]{{"01/45", "23/67"}, {"89/cd", "ab/ef"}});
        assertThat(subdivide("012345/6789ab/cdefgh/ijklmn/opqrst/uvwxyz")).isEqualTo(new String[][]{{"01/67", "23/89", "45/ab"}, {"cd/ij", "ef/kl", "gh/mn"}, {"op/uv", "qr/wx", "st/yz"}});
    }

    @Test
    void subdivide3Example() {
        assertThat(subdivide("012/345/678")).isEqualTo(new String[][]{{"012/345/678"}});
    }

    @Test
    void mergeExample() {
        assertThat(merge(new String[][]{{"##./#../...", "##./#../..."}, {"##./#../...", "##./#../..."}})).isEqualTo("##.##./#..#../....../##.##./#..#../......");
    }

    @Test
    void example() {
        Map<String, String> rules = createRules("../.# => ##./#../...\r\n.#./..#/### => #..#/..../..../#..#");

        String result = cycle(rules, START);

        assertThat(result).isEqualTo("#..#/..../..../#..#");

        result = cycle(rules, result);

        assertThat(result).isEqualTo("##.##./#..#../....../##.##./#..#../......");
    }
}