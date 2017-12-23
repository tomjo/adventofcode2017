package be.tomjo.advent.day16;

import java.util.List;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Dance {

    public static void main(String[] args) {
        String input = readInput("16.txt");
        System.out.println("Solution 16.1: " + benchmark(()->part1(input)));
        System.out.println("Solution 16.2: " + benchmark(()->part2(input)));
    }

    public static String part1(String input) {
        List<String> moves = asList(input.split(","));
        return performDance(moves, 16, 1);
    }

    public static String part2(String input) {
        List<String> moves = asList(input.split(","));
        return performDance(moves, 16, 1000000000);
    }

    private static List<Character> generatePrograms(int n) {
        return range(0, n).map(i -> i + 'a').mapToObj(c -> (char) c).collect(toList());
    }

    public static String performDance(List<String> moves, int programCount, int iterationCount) {
        List<Character> programs = generatePrograms(programCount);
        String startPosition = programs.stream().map(c -> c + "").collect(joining());
        Dance dance = new Dance(programs);
        List<DanceMove> danceRoutine = moves.stream().map(Dance::createDanceMove).collect(toList());
        for (long i = 0; i < iterationCount; i++) {
            dance.dance(danceRoutine);
            if (cycleFound(startPosition, dance)) {
                i += (iterationCount/(i+1)-1) * (i+1);
            }
        }
        return dance.getPrograms();
    }

    private static boolean cycleFound(String startPosition, Dance dance) {
        return dance.getPrograms().equals(startPosition);
    }

    private List<Character> programs;


    public Dance(List<Character> programs) {
        this.programs = programs;
    }

    public void dance(List<DanceMove> danceRoutine) {
        danceRoutine.forEach(d -> d.dance(programs));
    }

    public static DanceMove createDanceMove(String move) {
        String[] params = move.substring(1).split("/");
        switch (move.charAt(0)) {
            case 's':
                return new Spin(parseInt(params[0]));
            case 'x':
                return new Exchange(parseInt(params[0]), parseInt(params[1]));
            case 'p':
                return new Partner(params[0].charAt(0), params[1].charAt(0));
        }
        throw new IllegalStateException();
    }

    public String getPrograms() {
        return programs.stream().map(String::valueOf).collect(joining());
    }
}
