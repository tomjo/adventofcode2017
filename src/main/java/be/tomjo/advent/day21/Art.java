package be.tomjo.advent.day21;

import java.util.Collection;
import java.util.Map;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

public class Art {

    public static final String START = ".#./..#/###";

    public static void main(String[] args) {
        String input = readInput("21.txt");
        System.out.println("Solution 21.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 21.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input){
        Map<String, String> rules = createRules(input);
        String result = cycle(rules, START);
        for (int i = 0; i < 4; i++) {
            result = cycle(rules, result);
        }
        return countHashTags(result);
    }

    public static int part2(String input){
        Map<String, String> rules = createRules(input);
        String result = cycle(rules, START);
        for (int i = 0; i < 17; i++) {
            result = cycle(rules, result);
        }
        return countHashTags(result);
    }

    private static int countHashTags(String result) {
        return (int) result.chars().filter(c -> c == '#').count();
    }

    public static Map<String, String> createRules(String input) {
        return stream(input.split("\r\n"))
                .map(Rule::new)
                .map(Rule::getVariations)
                .flatMap(Collection::stream)
                .collect(toMap(Rule::getInput, Rule::getOutput));
    }

    public static String cycle(Map<String, String> rules, String gridString) {
        String[][] divided = subdivide(gridString);
        enhance(rules, divided);
        return merge(divided);
    }

    static String[][] subdivide(String gridString) {
        char[][] grid = stringToGrid(gridString);
        int size = gridString.split("/").length % 2 == 0 ? 2 : 3;
        String[][] result = new String[grid.length / size][grid[0].length / size];
        for (int row = 0; row < grid.length; row += size) {
            for (int col = 0; col < grid[0].length; col += size) {
                char[][] subGrid = createSubGrid(grid, size, row, col);
                result[row / size][col / size] = gridToString(subGrid);
            }
        }
        return result;
    }

    private static char[][] createSubGrid(char[][] grid, int size, int row, int col) {
        char[][] subGrid = new char[size][size];
        for (int subGridRow = 0; subGridRow < subGrid.length; subGridRow++) {
            for (int subGridCol = 0; subGridCol < subGrid[0].length; subGridCol++) {
                subGrid[subGridRow][subGridCol] = grid[row + subGridRow][col + subGridCol];
            }
        }
        return subGrid;
    }

    private static void enhance(Map<String, String> rules, String[][] divided) {
        for (int row = 0; row < divided.length; row++) {
            for (int col = 0; col < divided[0].length; col++) {
                String enhanced = rules.get(divided[row][col]);
                if (enhanced == null) {
                    throw new IllegalStateException("No rule for " + divided[row][col]);
                }
                divided[row][col] = enhanced;
            }
        }
    }

    static String merge(String[][] grid) {
        int size = grid[0][0].split("/").length;
        StringBuilder stringBuilder = new StringBuilder();
        range(0, grid.length).forEach(partRow -> createRowsFromPart(grid[partRow], stringBuilder, size));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private static void createRowsFromPart(String[] array, StringBuilder sb, int size) {
        range(0, size)
                .mapToObj(i -> createNewRow(array, i))
                .map(sb::append)
                .forEach(s -> s.append("/"));
    }

    private static String createNewRow(String[] array, int i) {
        return stream(array)
                .map(s -> s.split("/")[i])
                .collect(joining());
    }

    static String rotate90CW(String grid) {
        char[][] chars = stringToGrid(grid);
        char[][] result = new char[chars.length][chars[0].length];
        for (int x = 0; x < chars.length; x++) {
            for (int y = 0; y < chars[0].length; y++) {
                result[x][y] = chars[chars.length - 1 - y][x];
            }
        }
        return gridToString(result);
    }

    static String horizontalFlip(String grid) {
        return stream(grid.split("/"))
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .collect(joining("/"));
    }

    private static String gridToString(char[][] result) {
        return stream(result).map(String::new).collect(joining("/"));
    }

    private static char[][] stringToGrid(String grid) {
        return stream(grid.split("/")).map(String::toCharArray).toArray(char[][]::new);
    }

}
