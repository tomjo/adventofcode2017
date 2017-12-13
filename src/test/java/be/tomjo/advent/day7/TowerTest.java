package be.tomjo.advent.day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TowerTest {

    @Test
    void parseTower_noChildren() {
        String representation = "ebii (61)";

        Tower result = new Tower(representation);

        assertThat(result.getName()).isEqualTo("ebii");
        assertThat(result.getWeight()).isEqualTo(61);
        assertThat(result.getChildren()).isEmpty();
    }

    @Test
    void parseTower_children() {
        String representation = "fwft (72) -> ktlj, cntj";

        Tower result = new Tower(representation);

        assertThat(result.getName()).isEqualTo("fwft");
        assertThat(result.getWeight()).isEqualTo(72);
        assertThat(result.getChildren()).containsExactly("ktlj", "cntj");
    }
}