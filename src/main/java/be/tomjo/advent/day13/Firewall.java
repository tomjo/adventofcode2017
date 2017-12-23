package be.tomjo.advent.day13;

import be.tomjo.advent.IntHolder;

import java.util.Map;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class Firewall {

    public static void main(String[] args) {
        String input = readInput("13.txt");
        System.out.println("Solution 13.1: "+benchmark(()->part1(input)));
        System.out.println("Solution 13.2: "+benchmark(()->part2(input)));
    }

    public static int part1(String input){
        Map<Integer, Integer> firewall = createFirewall(input);
        return getSeverity(firewall);
    }

    public static int part2(String input){
        Map<Integer, Integer> firewall = createFirewall(input);
        return fewestStepsToPass(firewall);
    }

    private static Map<Integer, Integer> createFirewall(String input) {
        return stream(input.split("\r\n")).map(s -> s.split(": ")).collect(toMap(p -> parseInt(p[0]), p -> parseInt(p[1])));
    }

    public static int fewestStepsToPass(Map<Integer,Integer> firewall){
        int delay = 0;
        while(!tryPassingWithDelay(firewall, delay)){
            delay++;
        }
        return delay;
    }

    private static boolean tryPassingWithDelay(Map<Integer, Integer> firewall, int delay) {
        boolean passes = true;
        for (Map.Entry<Integer, Integer> e : firewall.entrySet()) {
            passes = !getsCaughtInLayer(e.getKey() + delay, e.getValue());
            if(!passes)
                break;
        }
        return passes;
    }

    public static int getSeverity(Map<Integer,Integer> firewall){
        return firewall.entrySet().stream()
                .filter(e -> getsCaughtInLayer(e.getKey(), e.getValue()))
                .mapToInt(e -> e.getKey() * e.getValue())
                .sum();
    }

    private static boolean getsCaughtInLayer(int layer, int depth) {
        //layer0    =   caught at   0, 4, 8
        //layer1    =   caught at   1, 3, 5
        //layer4    =   caught at   4, 10, 16
        //layern    =   caught at   n, n+(depth-1)*2, n+(depth-1)*2+(depth-1)*2
        return layer % ((depth-1)*2) == 0;
    }
}
