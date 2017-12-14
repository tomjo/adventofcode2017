package be.tomjo.advent.day8;

import java.util.HashMap;
import java.util.Map;

public class Registers {
    private static final int REGISTER_DEFAULT_VALUE = 0;

    private Map<String,Integer> registers;

    public Registers(){
        registers = new HashMap<>();
    }

    public void put(String registerName, int value){
        registers.put(registerName, value);
    }

    public int get(String registerName){
        return registers.getOrDefault(registerName, REGISTER_DEFAULT_VALUE);
    }

    public int getHighestValue(){
        return registers.values().stream().mapToInt(i -> i).max().orElse(REGISTER_DEFAULT_VALUE);
    }

}
