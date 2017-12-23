package be.tomjo.advent.day23;

import be.tomjo.advent.day18.InstructionContext;
import be.tomjo.advent.day18.InstructionInstance;

import java.util.List;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static be.tomjo.advent.day18.Duet.createInstructions;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;

public class Coprocessor {

    public static void main(String[] args) {
        String input = readInput("23.txt");
        System.out.println("Solution 23.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 23.2: " + benchmark(() -> part2(input)));
    }

    public static long part1(String input) {
        String[] instructionLines = input.split("\r\n");
        int mulCount = 0;
        InstructionContext instructionContext = new InstructionContext(instructionLines.length, null, null);
        List<InstructionInstance> instructions = createInstructions(instructionLines, instructionContext);

        while (instructionContext.isActive()) {
            InstructionInstance currentInstruction = instructions.get(instructionContext.getCurrentInstruction());
            currentInstruction.execute();
            if (currentInstruction.getName().equals("mul")) {
                mulCount++;
            }
        }
        return mulCount;
    }

    public static int part2(String input){
        String[] instructionLines = input.split("\r\n");
        int startValue = findStartValue(instructionLines);
        return (int) iterate(startValue, n -> n + 17).limit(1001).filter(x -> !isPrime(x)).count();
    }

    private static int findStartValue(String[] instructionLines) {
        InstructionContext instructionContext = new InstructionContext(instructionLines.length, null, null);
        instructionContext.setRegister("a", "1");
        instructionContext.setCurrentInstruction(0);
        List<InstructionInstance> instructions = createInstructions(instructionLines, instructionContext);

        while (instructionContext.isActive()) {
            InstructionInstance currentInstruction = instructions.get(instructionContext.getCurrentInstruction());
            currentInstruction.execute();
            if(currentInstruction.getName().equals("sub") && currentInstruction.getParams()[0].equals("c") && currentInstruction.getParams()[1].equals("-17000")){
                break;
            }
        }
        return (int) instructionContext.getRegisterValue("b");
    }

    private static boolean isPrime(int n){
        return range(2, n).noneMatch(i -> n % i == 0);
    }

}
