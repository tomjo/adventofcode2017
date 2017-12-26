package be.tomjo.advent.day25;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class TuringMachine {

    private List<Integer> tape;
    private int cursor;
    private State currentState;
    private Map<String, State> states;

    public TuringMachine(State initialState, Collection<State> states) {
        this.currentState = initialState;
        this.states = states.stream().collect(toMap(State::getName, identity()));
        this.tape = new ArrayList<>();
        this.tape.add(0);
    }

    public void step(){
        int currentValue = tape.get(cursor);
        ConditionalAction action = currentState.getAction(currentValue);
        tape.set(cursor, action.getWriteValue());
        cursor += action.getDirection();
        if(cursor >= tape.size()){
            tape.add(0);
        }else if(cursor < 0){
            tape.add(0, 0);
            cursor = 0;
        }
        currentState = states.get(action.getNextState());
    }

    public int diagnosticChecksum(){
        return tape.stream().mapToInt(Integer::intValue).sum();
    }

}
