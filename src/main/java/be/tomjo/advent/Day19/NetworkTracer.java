package be.tomjo.advent.Day19;

import java.util.stream.IntStream;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Character.isLetter;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class NetworkTracer {

    public static void main(String[] args) {
        String input = readInput("19.txt");
        System.out.println("Solution 19.1: " + benchmark(()->part1(input)));
        System.out.println("Solution 19.2: " + benchmark(()->part2(input)));
    }

    public static String part1(String input){
        int[][] network = createNetwork(input);
        NetworkTracer networkTracer = new NetworkTracer(network);
        while(networkTracer.move());
        return networkTracer.getEncounteredLetters();
    }

    public static int part2(String input){
        int[][] network = createNetwork(input);
        NetworkTracer networkTracer = new NetworkTracer(network);
        int steps = 0;
        while(networkTracer.move()){
            steps++;
        }
        return steps;
    }

    private int[][] network;
    private int curX;
    private int curY;
    private int xDirection;
    private int yDirection;
    private StringBuilder encounteredLetters;

    public NetworkTracer(int[][] network) {
        this.network = network;
        this.curX = findStartingPoint(network[0]);
        this.curY = 0;
        this.xDirection = 0;
        this.yDirection = 1;
        this.encounteredLetters = new StringBuilder();
    }

    public boolean move() {
        int curPos = network[curY][curX];
        if (isLetter(curPos)) {
            encounteredLetters.append((char) curPos);
        } else if (curPos == '+') {
            handleDirectionChange();
        } else if (curPos == ' ') {
            return false;
        }
        moveToNextNodeOnPath();
        return true;
    }

    private void moveToNextNodeOnPath() {
        curX += xDirection;
        curY += yDirection;
    }

    private void handleDirectionChange() {
        if (yDirection != 0) {
            if (shouldMoveLeft(network, curX, curY)) {
                xDirection = -1;
                yDirection = 0;
            } else if (shouldMoveRight(network, curX, curY)) {
                xDirection = 1;
                yDirection = 0;
            }
        } else {
            if (shouldMoveUp(network, curX, curY)) {
                xDirection = 0;
                yDirection = -1;
            } else if (shouldMoveDown(network, curX, curY)) {
                xDirection = 0;
                yDirection = 1;
            }
        }
    }

    public String getEncounteredLetters() {
        return encounteredLetters.toString();
    }

    private static boolean shouldMoveDown(int[][] network, int curX, int curY) {
        int neighbour = network[curY + 1][curX];
        return isLetter(neighbour) || neighbour == '|';
    }

    private static boolean shouldMoveUp(int[][] network, int curX, int curY) {
        int neighbour = network[curY - 1][curX];
        return isLetter(neighbour) || neighbour == '|';
    }

    private static boolean shouldMoveLeft(int[][] network, int curX, int curY) {
        int neighbour = network[curY][curX - 1];
        return isLetter(neighbour) || neighbour == '-';
    }

    private static boolean shouldMoveRight(int[][] network, int curX, int curY) {
        int neighbour = network[curY][curX + 1];
        return isLetter(neighbour) || neighbour == '-';
    }

    public static int[][] createNetwork(String input) {
        String[] rows = input.split("\n");
        return stream(rows)
                .map(String::chars)
                .map(IntStream::toArray)
                .toArray(int[][]::new);
    }

    private static int findStartingPoint(int[] ints) {
        return range(0, ints.length)
                .filter(i -> ints[i] == '|')
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid network"));
    }
}
