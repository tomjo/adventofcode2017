package be.tomjo.advent.day9;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day9.Groups.process;
import static org.assertj.core.api.Assertions.assertThat;

class GroupsTest {


    @Test
    void emptyGroup_1() {
        assertThat(process("{}").getScore()).isEqualTo(1);
    }

    @Test
    void nestedThreeGroups_6() {
        assertThat(process("{{{}}}").getScore()).isEqualTo(6);
    }

    @Test
    void nestedTwoGroupsSameLevel_5() {
        assertThat(process("{{},{}}").getScore()).isEqualTo(5);
    }

    @Test
    void moreExamples() {
        assertThat(process("{{{},{},{{}}}}").getScore()).isEqualTo(16);
        assertThat(process("{<a>,<a>,<a>,<a>}").getScore()).isEqualTo(1);
        assertThat(process("{{<ab>},{<ab>},{<ab>},{<ab>}}").getScore()).isEqualTo(9);
        assertThat(process("{{<!!>},{<!!>},{<!!>},{<!!>}}").getScore()).isEqualTo(9);
        assertThat(process("{{<a!>},{<a!>},{<a!>},{<ab>}}").getScore()).isEqualTo(3);
    }

    @Test
    void garbageExamples() {
        assertThat(process("<>").getGarbage()).isEqualTo(0);
        assertThat(process("<random characters>").getGarbage()).isEqualTo(17);
        assertThat(process("<<<<>").getGarbage()).isEqualTo(3);
        assertThat(process("<{!>}>").getGarbage()).isEqualTo(2);
        assertThat(process("<!!>").getGarbage()).isEqualTo(0);
        assertThat(process("<!!!>>").getGarbage()).isEqualTo(0);
        assertThat(process("<{o\"i!a,<{i<a>").getGarbage()).isEqualTo(10);
    }
}