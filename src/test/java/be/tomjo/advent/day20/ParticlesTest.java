package be.tomjo.advent.day20;

import org.junit.jupiter.api.Test;

import static be.tomjo.advent.day20.Particles.part1;
import static be.tomjo.advent.day20.Particles.part2;
import static org.assertj.core.api.Assertions.assertThat;

class ParticlesTest {

    @Test
    void example() {
        String input = "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\r\np=<4,0,0>, v=<0,0,0>, a=<-2,0,0>";
        assertThat(part1(input)).isEqualTo(0);
    }

    @Test
    void example2() {
        String input = "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>\r\np=<-4,0,0>, v=<2,0,0>, a=<0,0,0>\r\np=<-2,0,0>, v=<1,0,0>, a=<0,0,0>\r\np=<3,0,0>, v=<-1,0,0>, a=<0,0,0>";
        assertThat(part2(input)).isEqualTo(1);
    }
}