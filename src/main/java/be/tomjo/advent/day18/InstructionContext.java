package be.tomjo.advent.day18;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

public class InstructionContext {
    private Map<String,Long> registers;

    private long frequency;
    private int maxInstructionCount;
    private int currentInstruction;
    private boolean active;

    public InstructionContext(int maxInstructionCount) {
        this.maxInstructionCount = maxInstructionCount;
        this.registers = new HashMap<>();
        this.active = true;
    }

    public void afterExecution() {
        if(currentInstruction < 0 || currentInstruction >= maxInstructionCount){
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public int getCurrentInstruction() {
        return currentInstruction;
    }

    public long getFrequency() {
        return frequency;
    }

    private long getValueOrRegisterValue(Object value) {
        try{
            return l(value);
        }catch (NumberFormatException nfe){
            return getRegisterValue(value);
        }
    }

    private long getRegisterValue(Object register){
        return registers.getOrDefault(s(register), 0L);
    }

    public void setRegister(Object register, Object value) {
        registers.put(s(register), getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void addRegister(Object register, Object value) {
        registers.put(s(register), getRegisterValue(register)+getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void mulRegister(Object register1, Object register2) {
        registers.put(s(register1), getRegisterValue(register1)*getValueOrRegisterValue(register2));
        currentInstruction++;
    }

    public void modRegister(Object register, Object value) {
        registers.put(s(register), getRegisterValue(register)% getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void recoverFrequency(Object register) {
        if(getRegisterValue(register) != 0){
            active = false;
        }else {
            currentInstruction++;
        }
    }

    public void sendSound(Object register) {
        frequency = getRegisterValue(register);
        currentInstruction++;
    }

    public void jgz(Object register, Object offset) {
        if(getRegisterValue(register) > 0){
            currentInstruction += l(offset);
        }else{
            currentInstruction++;
        }
    }

    private static String s(Object param){
        return (String)param;
    }

    private static long l(Object param){
        return parseLong((String)param);
    }
}
