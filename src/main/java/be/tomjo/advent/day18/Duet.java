package be.tomjo.advent.day18;

import java.util.List;

import static be.tomjo.advent.Util.readInput;
import static be.tomjo.advent.day18.InstructionFactory.createInstruction;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Duet {

    public static void main(String[] args) {
        String[] instructionLines = readInput("18.txt").split("\r\n");
        System.out.println("Solution 18.1: " + part1(instructionLines));
        System.out.println("Solution 18.2: " + part2(instructionLines));
    }

    public static long part1(String[] instructionLines) {
        ProgramPipe<Long> receiveQueue = new ProgramPipe<>();
        InstructionContext instructionContext = new InstructionContext(instructionLines.length, receiveQueue, receiveQueue);

        LastValueRecordingListener lastValueListener = new LastValueRecordingListener();
        receiveQueue.addSendListener(lastValueListener);
        receiveQueue.addReceiveListener(new StopExecutionOnNonZeroReceiveListener(instructionContext));

        List<InstructionInstance> instructions = createInstructions(instructionLines, instructionContext);

        while (instructionContext.isActive()) {
            InstructionInstance currentInstruction = instructions.get(instructionContext.getCurrentInstruction());
            currentInstruction.execute();
        }

        return lastValueListener.getLastReceivedValue();
    }

    public static long part2(String[] instructionLines) {
        ProgramPipe<Long> receiveQueue0 = new ProgramPipe<>();
        ProgramPipe<Long> receiveQueue1 = new ProgramPipe<>();
        CountingValueListener countingValueListener = new CountingValueListener();
        receiveQueue0.addSendListener(countingValueListener);

        InstructionContext instructionContext0 = createInstructionContext(instructionLines, receiveQueue0, receiveQueue1, "0");
        InstructionContext instructionContext1 = createInstructionContext(instructionLines, receiveQueue1, receiveQueue0, "1");

        List<InstructionInstance> instructions0 = createInstructions(instructionLines, instructionContext0);
        List<InstructionInstance> instructions1 = createInstructions(instructionLines, instructionContext1);

        while (!isDeadlocked(instructionContext0, instructionContext1, instructions0, instructions1)) {
            instructions0.get(instructionContext0.getCurrentInstruction()).execute();
            instructions1.get(instructionContext1.getCurrentInstruction()).execute();
        }
        return countingValueListener.getCount();
    }

    public static List<InstructionInstance> createInstructions(String[] instructionLines, InstructionContext instructionContext0) {
        return stream(instructionLines)
                .map(i -> createInstruction(i, instructionContext0))
                .collect(toList());
    }

    private static InstructionContext createInstructionContext(String[] instructionLines, ProgramPipe<Long> receiveQueue0, ProgramPipe<Long> receiveQueue1, String s) {
        InstructionContext instructionContext0 = new InstructionContext(instructionLines.length, receiveQueue0, receiveQueue1);
        instructionContext0.setRegister("p", s);
        instructionContext0.setCurrentInstruction(0);
        return instructionContext0;
    }

    private static boolean isDeadlocked(InstructionContext instructionContext0, InstructionContext instructionContext1, List<InstructionInstance> instructions0, List<InstructionInstance> instructions1) {
        return isBlocked(instructionContext0, instructions0) && isBlocked(instructionContext1, instructions1);
    }

    private static boolean isBlocked(InstructionContext instructionContext, List<InstructionInstance> instructions) {
        if (instructionContext.isBlocked()) {
            instructions.get(instructionContext.getCurrentInstruction()).execute();
            return instructionContext.isBlocked();
        }
        return false;
    }

}
