package be.tomjo.advent.day18;

import static java.util.Arrays.deepToString;

public class InstructionInstance {
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

    public void execute() {
        instruction.execute(instructionContext, params);
        instructionContext.afterExecution();
    }

    public String getName() {
        return name;
    }

    public Object[] getParams() {
        return params;
    }

    @Override
    public String toString() {
        return name + " " + deepToString(params);
    }
}
