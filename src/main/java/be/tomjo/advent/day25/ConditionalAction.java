package be.tomjo.advent.day25;

public class ConditionalAction {

    private final int condition;
    private final int writeValue;
    private final int direction;
    private final String nextState;

    public ConditionalAction(int condition, int writeValue, int direction, String nextState) {
        this.condition = condition;
        this.writeValue = writeValue;
        this.direction = direction;
        this.nextState = nextState;
    }

    public int getCondition() {
        return condition;
    }

    public int getWriteValue() {
        return writeValue;
    }

    public int getDirection() {
        return direction;
    }

    public String getNextState() {
        return nextState;
    }
}
