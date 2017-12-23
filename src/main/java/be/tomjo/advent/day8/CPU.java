package be.tomjo.advent.day8;

import java.util.List;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CPU {

    private Registers registers;

    public static void main(String[] args) {
        String input = readInput("8.txt");
        System.out.println("Solution 8.1: "+benchmark(()->part1(input)));
        System.out.println("Solution 8.2: "+benchmark(()->part2(input)));
    }

    public static int part1(String input){
        List<Instruction> instructions = stream(input.split("\r\n"))
                .map(Instruction::instruction)
                .collect(toList());
        CPU cpu = new CPU();
        instructions.forEach(cpu::executeInstruction);
        return cpu.getHighestCurrentRegisterValue();
    }

    public static int part2(String input){
        List<Instruction> instructions = stream(input.split("\r\n"))
                .map(Instruction::instruction)
                .collect(toList());
        CPU cpu = new CPU();
        instructions.forEach(cpu::executeInstruction);
        return cpu.getHighestEncounteredRegisterValue();
    }

    public CPU() {
        this.registers = new Registers();
    }

    public void executeInstruction(Instruction instruction){
        if(instruction.getCondition().applies(registers)){
            int result = instruction.getTransformation().applyAsInt(registers.get(instruction.getRegister()), instruction.getValue());
            registers.put(instruction.getRegister(), result);
        }
    }


    public int getHighestCurrentRegisterValue() {
        return registers.getHighestCurrentValue();
    }

    public int getHighestEncounteredRegisterValue() {
        return registers.getHighestEncounteredValue();
    }
}
