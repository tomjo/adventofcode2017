package be.tomjo.advent.day18;

@FunctionalInterface
interface Instruction {
    void execute(InstructionContext instructionContext, Object[] params);
}
