package be.tomjo.advent.day18;

import java.util.List;

import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Duet {

    public static void main(String[] args) {
        String[] instructionLines = readInput("18.txt").split("\r\n");
        System.out.println("Solution 18.1: "+part1(instructionLines));
    }

    public static long part1(String[] instructionLines) {
        ProgramPipe<Long> receiveQueue = new ProgramPipe<>();
        InstructionContext instructionContext = new InstructionContext(instructionLines.length, receiveQueue, receiveQueue);

        LastValueRecordingListener lastValueListener = new LastValueRecordingListener();
        receiveQueue.addSendListener(lastValueListener);
        receiveQueue.addReceiveListener(new StopExecutionOnNonZeroReceiveListener(instructionContext));

        List<InstructionInstance> instructions = stream(instructionLines)
                .map(i -> InstructionFactory.createInstruction(i, instructionContext))
                .collect(toList());

        while(instructionContext.isActive()){
            InstructionInstance currentInstruction = instructions.get(instructionContext.getCurrentInstruction());
            currentInstruction.execute();
        }

        return lastValueListener.getLastReceivedValue();
    }


}
