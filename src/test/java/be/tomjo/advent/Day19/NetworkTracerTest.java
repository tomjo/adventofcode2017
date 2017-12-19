package be.tomjo.advent.Day19;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.Day19.NetworkTracer.createNetwork;
import static java.lang.String.join;
import static org.assertj.core.api.Assertions.assertThat;

class NetworkTracerTest {

    @Test
    void example() {
        String input = join("\r\n",
                "     |          ",
                "     |  +--+    ",
                "     A  |  C    ",
                " F---|----E|--+ ",
                "     |  |  |  D ",
                "     +B-+  +--+ ");

        int[][] network = createNetwork(input);
        NetworkTracer networkTracer = new NetworkTracer(network);
        int steps = 0;
        while(networkTracer.move()){
            steps++;
        }
        assertThat(networkTracer.getEncounteredLetters()).isEqualTo("ABCDEF");
        assertThat(steps).isEqualTo(38);
    }
}