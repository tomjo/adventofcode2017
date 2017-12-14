package be.tomjo.advent.day8;

import be.tomjo.advent.Util;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CPU {

    private Registers registers;

    public static void main(String[] args) {
        String input = Util.readInput("8.txt");
        List<Instruction> instructions = Arrays.stream(input.split("\r\n")).map(Instruction::instruction).collect(toList());
        CPU cpu = new CPU();
        instructions.forEach(cpu::executeInstruction);
        System.out.println("Solution 8.1: "+cpu.getHighestRegisterValue());
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


    public int getHighestRegisterValue() {
        return registers.getHighestValue();
    }
}
