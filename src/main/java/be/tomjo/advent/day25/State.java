package be.tomjo.advent.day25;

import java.util.HashMap;
import java.util.Map;

public class State {

    private final String name;
    private final Map<Integer,ConditionalAction> conditionalActions;

    public State(String name){
        this.name = name;
        this.conditionalActions = new HashMap<>();
    }

    public void addAction(ConditionalAction action) {
        this.conditionalActions.put(action.getCondition(), action);
    }

    public String getName() {
        return name;
    }

    public ConditionalAction getAction(int currentValue) {
        return conditionalActions.get(currentValue);
    }
}
