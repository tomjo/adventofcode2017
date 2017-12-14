package be.tomjo.advent.day8;

import java.util.function.IntBinaryOperator;

import static be.tomjo.advent.day8.Condition.condition;

public class Instruction {

    private final String register;
    private final IntBinaryOperator transformation;
    private final int value;
    private final Condition condition;


    public static Instruction instruction(String representation){
        String[] parts = representation.split(" ");
        String register = parts[0];
        IntBinaryOperator transformation = getTransformation(parts[1]);
        int value = Integer.parseInt(parts[2]);
        String registerToCheck = parts[4];
        String comparison = parts[5];
        int valueToCheck = Integer.parseInt(parts[6]);
        return new Instruction(register, transformation, value, condition(registerToCheck, comparison, valueToCheck));
    }

    private static IntBinaryOperator getTransformation(String transformation) {
        if(transformation.equals("inc")){
            return (a,b) -> a + b;
        }else if(transformation.equals("dec")){
            return (a,b) -> a - b;
        }
        throw new IllegalArgumentException("Unknown transformation "+transformation);
    }

    private Instruction(String register, IntBinaryOperator transformation, int value, Condition condition) {
        this.register = register;
        this.transformation = transformation;
        this.condition = condition;
        this.value = value;
    }

    public String getRegister() {
        return register;
    }

    public IntBinaryOperator getTransformation() {
        return transformation;
    }

    public int getValue() {
        return value;
    }

    public Condition getCondition() {
        return condition;
    }
}
