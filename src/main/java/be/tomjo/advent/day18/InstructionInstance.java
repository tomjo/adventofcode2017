package be.tomjo.advent.day18;

import static java.util.Arrays.deepToString;

class InstructionInstance {
    private final InstructionContext instructionContext;
    private final Instruction instruction;
    private final String name;
    private final Object[] params;

    public InstructionInstance(String name, InstructionContext instructionContext, Instruction instruction, Object[] params) {
        this.name = name;
        this.instructionContext = instructionContext;
        this.instruction = instruction;
        this.params = params;
    }

    void execute(){
        instruction.execute(instructionContext, params);
        instructionContext.afterExecution();
    }

    @Override
    public String toString() {
        return name+" "+ deepToString(params);
    }
}
