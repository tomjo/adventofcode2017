package be.tomjo.advent.day16;

import java.util.List;

import static be.tomjo.advent.Util.readInput;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.rotate;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Dance {

    public static void main(String[] args) {
        List<String> moves = asList(readInput("16.txt").split(","));
        System.out.println("Solution 16.1: " + part1(moves, 16));
    }

    public static String part1(List<String> moves, int programCount) {
        Dance dance = new Dance(programCount);
        moves.forEach(dance::dance);
        return dance.getPrograms();
    }

    private List<Character> programs;

    public Dance(int n) {
        programs = generatePrograms(n);
    }

    public void dance(String move) {
        String[] params = move.substring(1).split("/");
        switch (move.charAt(0)) {
            case 's':
                rotate(programs, parseInt(params[0]));
                break;
            case 'x':
                exchange(parseInt(params[0]), parseInt(params[1]));
                break;
            case 'p':
                partner(params[0].charAt(0), params[1].charAt(0));
                break;
        }
    }

    private void exchange(int position1, int position2) {
        char temp = programs.get(position1);
        programs.set(position1, programs.get(position2));
        programs.set(position2, temp);
    }

    private void partner(char program1, char program2) {
        int position1 = programs.indexOf(program1);
        int position2 = programs.indexOf(program2);
        exchange(position1, position2);
    }

    private List<Character> generatePrograms(int n) {
        return range(0, n).map(i -> i + 'a').mapToObj(c -> (char) c).collect(toList());
    }

    public String getPrograms() {
        return programs.stream().map(String::valueOf).collect(joining());
    }
}
