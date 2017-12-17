package be.tomjo.advent.day13;

import java.util.Map;

import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class Firewall {

    public static void main(String[] args) {
        Map<Integer, Integer> firewall = stream(readInput("13.txt").split("\r\n")).map(s -> s.split(": ")).collect(toMap(p -> parseInt(p[0]), p -> parseInt(p[1])));

        System.out.println("Solution 13.1: "+getSeverity(firewall));
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
