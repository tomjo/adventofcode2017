package be.tomjo.advent.day7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TowerCalculatorTest {

    @Test
    void findBottomTower() {
        String[] representations = new String[]{
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)"
        };

        Tower result = TowerCalculator.findBottomTower(representations);

        assertThat(result.getName()).isEqualTo("tknk");
    }
}