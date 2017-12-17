package be.tomjo.advent.day13;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day13.Firewall.getSeverity;
import static com.google.common.collect.ImmutableMap.of;
import static org.assertj.core.api.Assertions.assertThat;

class FirewallTest {

    @Test
    void example() {
        assertThat(getSeverity(of(0, 3, 1, 2, 4, 4, 6, 4))).isEqualTo(24);
    }
}