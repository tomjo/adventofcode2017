package be.tomjo.advent.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;

class CPUTest {

    @Test
    void example() {
        CPU cpu = new CPU();
        List<Instruction> instructions = of(
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10")
                .map(Instruction::instruction)
                .collect(Collectors.toList());

        instructions.forEach(cpu::executeInstruction);

        assertThat(cpu.getHighestRegisterValue()).isEqualTo(1);
    }
}