package be.tomjo.advent.day18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class InstructionFactory {

    private static final Map<String, Instruction> INSTRUCTION_MAP;

    static {
        INSTRUCTION_MAP = new HashMap<>();
        INSTRUCTION_MAP.put("snd", (ctx,p) -> ctx.send(p[0]));
        INSTRUCTION_MAP.put("rcv", (ctx,p) -> ctx.receive(p[0]));

        INSTRUCTION_MAP.put("set", (ctx,p) -> ctx.setRegister(p[0], p[1]));
        INSTRUCTION_MAP.put("add", (ctx,p) -> ctx.addRegister(p[0], p[1]));
        INSTRUCTION_MAP.put("mul", (ctx,p) -> ctx.mulRegister(p[0], p[1]));
        INSTRUCTION_MAP.put("mod", (ctx,p) -> ctx.modRegister(p[0], p[1]));

        INSTRUCTION_MAP.put("jgz", (ctx,p) -> ctx.jgz(p[0], p[1]));
    }

    public static InstructionInstance createInstruction(String instruction, InstructionContext instructionContext){
        List<String> parts = asList(instruction.split(" "));
        String name = parts.get(0);
        Object[] params = parts.subList(1, parts.size()).toArray();
        return new InstructionInstance(name, instructionContext, INSTRUCTION_MAP.get(name), params);
    }
}
