package be.tomjo.advent.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GroupsTest {


    @Test
    void emptyGroup_1() {
        assertThat(Groups.score("{}")).isEqualTo(1);
    }

    @Test
    void nestedThreeGroups_6() {
        assertThat(Groups.score("{{{}}}")).isEqualTo(6);
    }

    @Test
    void nestedTwoGroupsSameLevel_5() {
        assertThat(Groups.score("{{},{}}")).isEqualTo(5);
    }

    @Test
    void moreExamples() {
        assertThat(Groups.score("{{{},{},{{}}}}")).isEqualTo(16);
        assertThat(Groups.score("{<a>,<a>,<a>,<a>}")).isEqualTo(1);
        assertThat(Groups.score("{{<ab>},{<ab>},{<ab>},{<ab>}}")).isEqualTo(9);
        assertThat(Groups.score("{{<!!>},{<!!>},{<!!>},{<!!>}}")).isEqualTo(9);
        assertThat(Groups.score("{{<a!>},{<a!>},{<a!>},{<ab>}}")).isEqualTo(3);
    }
}