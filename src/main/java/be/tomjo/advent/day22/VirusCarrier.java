package be.tomjo.advent.day22;

import java.util.HashMap;
import java.util.Map;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static be.tomjo.advent.day22.Status.CLEAN;
import static be.tomjo.advent.day22.Status.INFECTED;

public class VirusCarrier {

    private Map<Node, Status> exploredNodes;
    private Node currentNode;
    private Direction direction;
    private int infectionCount;

    public static void main(String[] args) {
        String map = readInput("22.txt");
        System.out.println("Solution 22.1: " + benchmark(()->part1(map)));
        System.out.println("Solution 22.2: " + benchmark(()->part2(map)));
    }

    public static int part1(String map) {
        VirusCarrier virusCarrier = new VirusCarrier(map);
        for (int i = 0; i < 10000; i++) {
            virusCarrier.burst();
        }
        return virusCarrier.getInfectionCount();
    }

    public static int part2(String map) {
        VirusCarrier virusCarrier = new VirusCarrier(map);
        for (int i = 0; i < 10000000; i++) {
            virusCarrier.burst2();
        }
        return virusCarrier.getInfectionCount();
    }

    public VirusCarrier(String map) {
        this.exploredNodes = new HashMap<>();
        String[] rows = map.split("\r\n");
        for (int i = 0; i < rows.length; i++) {
            char[] chars = rows[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '#') {
                    exploredNodes.put(new Node(j - chars.length / 2, i - rows.length / 2), INFECTED);
                }
            }
        }
        this.currentNode = new Node(0, 0);
        this.direction = Direction.UP;
        this.infectionCount = 0;
    }

    public void burst() {
        boolean infected = exploredNodes.getOrDefault(currentNode, CLEAN) == INFECTED;
        direction = infected ? direction.turnRight() : direction.turnLeft();
        if (infected) {
            exploredNodes.remove(currentNode);
        } else {
            exploredNodes.put(currentNode, INFECTED);
            infectionCount++;
        }
        currentNode = currentNode.add(direction.getX(), direction.getY());
    }

    public void burst2() {
        Status status = exploredNodes.getOrDefault(currentNode, CLEAN);
        direction = status.turn(direction);
        Status nextStatus = status.next();
        exploredNodes.put(currentNode, nextStatus);
        if (nextStatus == INFECTED) {
            infectionCount++;
        }
        currentNode = currentNode.add(direction.getX(), direction.getY());
    }

    public int getInfectionCount() {
        return infectionCount;
    }
}
