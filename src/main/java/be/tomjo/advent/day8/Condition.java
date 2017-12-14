package be.tomjo.advent.day8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;

public final class Condition {

    private final IntPredicate predicate;
    private final String register;

    private static final Map<String, BiFunction<Integer, Integer, Boolean>> comparisons;

    static {
        comparisons = new HashMap<>();
        comparisons.put(">", (a, b) -> a > b);
        comparisons.put(">=", (a, b) -> a >= b);
        comparisons.put("<", (a, b) -> a < b);
        comparisons.put("<=", (a, b) -> a <= b);
        comparisons.put("==", Integer::equals);
        comparisons.put("!=", (a, b) -> !a.equals(b));
    }

    public static Condition condition(String register, String comparison, int value) {
        BiFunction<Integer, Integer, Boolean> comparisonFunction = comparisons.get(comparison);
        return new Condition(i -> comparisonFunction.apply(i, value), register);
    }

    private Condition(IntPredicate predicate, String register) {
        this.predicate = predicate;
        this.register = register;
    }

    public boolean applies(Registers registers){
        int registerValue = registers.get(register);
        return predicate.test(registerValue);
    }
}
