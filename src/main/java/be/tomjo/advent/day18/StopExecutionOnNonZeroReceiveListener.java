package be.tomjo.advent.day18;

public class StopExecutionOnNonZeroReceiveListener implements ValueListener<Long> {

    private final InstructionContext instructionContext;

    public StopExecutionOnNonZeroReceiveListener(InstructionContext instructionContext) {
        this.instructionContext = instructionContext;
    }

    @Override
    public void notify(Long v) {
        if(v != 0){
            instructionContext.stop();
        }
    }
}
