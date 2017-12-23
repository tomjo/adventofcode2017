package be.tomjo.advent.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static java.lang.Long.parseLong;

public class InstructionContext {
    private final Map<String, Long> registers;
    private final int maxInstructionCount;
    private final ProgramPipe<Long> receiveQueue;
    private final ProgramPipe<Long> sendQueue;

    private int currentInstruction;
    private boolean active;
    private boolean blocked;


    public InstructionContext(int maxInstructionCount, ProgramPipe<Long> receiveQueue, ProgramPipe<Long> sendQueue) {
        this.maxInstructionCount = maxInstructionCount;
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
        this.registers = new HashMap<>();
        this.active = true;
    }

    public void afterExecution() {
        if (currentInstruction < 0 || currentInstruction >= maxInstructionCount) {
            active = false;
        }
    }

    public void stop() {
        active = false;
    }

    public void setCurrentInstruction(int currentInstruction) {
        this.currentInstruction = currentInstruction;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isActive() {
        return active;
    }

    public int getCurrentInstruction() {
        return currentInstruction;
    }

    private long getValueOrRegisterValue(Object value) {
        try {
            return l(value);
        } catch (NumberFormatException nfe) {
            return getRegisterValue(value);
        }
    }

    public long getRegisterValue(Object register) {
        return registers.getOrDefault(s(register), 0L);
    }

    public void setRegister(Object register, Object value) {
        registers.put(s(register), getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void addRegister(Object register, Object value) {
        registers.put(s(register), getRegisterValue(register) + getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void subRegister(Object register, Object value) {
        registers.put(s(register), getRegisterValue(register) - getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void mulRegister(Object register1, Object register2) {
        registers.put(s(register1), getRegisterValue(register1) * getValueOrRegisterValue(register2));
        currentInstruction++;
    }

    public void modRegister(Object register, Object value) {
        registers.put(s(register), getRegisterValue(register) % getValueOrRegisterValue(value));
        currentInstruction++;
    }

    public void receive(Object register) {
        try {
            long received = receiveQueue.poll();
            blocked = false;
            registers.put(s(register), received);
            currentInstruction++;
        } catch (TimeoutException e) {
            blocked = true;
        }
    }

    public void send(Object register) {
        long value = getValueOrRegisterValue(register);
        sendQueue.add(value);
        currentInstruction++;
    }

    public void jgz(Object register, Object offset) {
        if (getValueOrRegisterValue(register) > 0) {
            currentInstruction += getValueOrRegisterValue(offset);
        } else {
            currentInstruction++;
        }
    }

    public void jnz(Object register, Object offset) {
        if (getValueOrRegisterValue(register) != 0) {
            currentInstruction += getValueOrRegisterValue(offset);
        } else {
            currentInstruction++;
        }
    }

    private static String s(Object param) {
        return (String) param;
    }

    private static long l(Object param) {
        return parseLong((String) param);
    }
}
