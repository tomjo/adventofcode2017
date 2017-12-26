package be.tomjo.advent.day25;

import be.tomjo.advent.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static be.tomjo.advent.Util.benchmark;
import static java.lang.Integer.parseInt;
import static java.util.Optional.empty;
import static java.util.stream.IntStream.range;

public class Day25 {

    private static final Pattern STATE_PATTERN = Pattern.compile("In state (\\w+):(?:\\s+If the current value is ([01]+):\\s+- Write the value ([01]+)\\.\\s+- Move one slot to the (left|right)\\.\\s+- Continue with state (\\w+)\\.)(?:\\s+If the current value is ([01]+):\\s+- Write the value ([01]+)\\.\\s+- Move one slot to the (left|right)\\.\\s+- Continue with state (\\w+)\\.)");
    private static final Pattern INITIAL_STATE_PATTERN = Pattern.compile("Begin in state (\\w+)\\.");
    private static final Pattern STEPS_PATTERN = Pattern.compile("Perform a diagnostic checksum after (\\d+) steps\\.");


    public static void main(String[] args) {
        String input = Util.readInput("25.txt");
        System.out.println("Solution 25: " + benchmark(() -> part1(input)));
    }

    public static int part1(String input) {
        Collection<State> states = parseStates(input);
        State initialState = parseInitialState(input, states).orElseThrow(IllegalArgumentException::new);
        int steps = parseSteps(input);
        TuringMachine turingMachine = new TuringMachine(initialState, states);
        range(0, steps).forEach(i -> turingMachine.step());
        return turingMachine.diagnosticChecksum();
    }

    private static int parseSteps(String input) {
        Matcher matcher = STEPS_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        return parseInt(matcher.group(1));
    }

    private static Optional<State> parseInitialState(String input, Collection<State> states) {
        Matcher matcher = INITIAL_STATE_PATTERN.matcher(input);
        if (!matcher.find()) {
            return empty();
        }
        String initialStateName = matcher.group(1);
        return states.stream()
                .filter(s -> s.getName().equals(initialStateName))
                .findFirst();
    }

    private static Collection<State> parseStates(String input) {
        Collection<State> states = new ArrayList<>();
        Matcher matcher = STATE_PATTERN.matcher(input);
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            State state = new State(matchResult.group(1));
            for (int group = 2; group < matchResult.groupCount(); group += 4) {
                int conditionalValue = parseInt(matchResult.group(group));
                int writeValue = parseInt(matchResult.group(group + 1));
                int direction = matchResult.group(group + 2).equals("left") ? -1 : 1;
                String nextState = matchResult.group(group + 3);
                state.addAction(new ConditionalAction(conditionalValue, writeValue, direction, nextState));
            }
            states.add(state);
        }
        return states;
    }
}
