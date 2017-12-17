package be.tomjo.advent.day12;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static be.tomjo.advent.day12.Pipes.getConnectedProgramSets;
import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class PipesTest {


    @Test
    void example1() {
        List<String> inputRecords = asList(
                "0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5");

        Collection<Set<String>> result = getConnectedProgramSets(inputRecords);
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(of("1"), of("0", "2", "3", "4", "5", "6"));
    }

}