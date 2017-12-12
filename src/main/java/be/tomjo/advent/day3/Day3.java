package be.tomjo.advent.day3;

import static be.tomjo.advent.day3.Point.point;
import static be.tomjo.advent.day3.SpiralMemoryPositionCalculator.calculatePosition;

public class Day3 {

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println("Solution 3.1: "+ day3.calculateManhattanDistanceFromNodeIdToAccessPort(361527));
        System.out.println("Solution 3.2: "+ day3.calculateNeighbourValueSum(361527));
    }

    public int calculateNeighbourValueSum(int nodeId){
        return createSpiralMemoryForStressTest(100).stream()
                .mapToInt(MemoryNode::getValue)
                .filter(i -> i > nodeId)
                .sorted()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Out of memory"));
    }

    public SpiralMemory createSpiralMemoryForStressTest(int capacity){
        SpiralMemory spiralMemory = new SpiralMemory();
        spiralMemory.addNode(new MemoryNode(1, 1));
        for(int currentId = 2; currentId < capacity; currentId++){
            Point currentPosition = calculatePosition(currentId);
            int value = spiralMemory.getValue(point(currentPosition.getX()-1, currentPosition.getY()-1))
                    + spiralMemory.getValue(point(currentPosition.getX()-1, currentPosition.getY()))
                    + spiralMemory.getValue(point(currentPosition.getX()-1, currentPosition.getY()+1))
                    + spiralMemory.getValue(point(currentPosition.getX(), currentPosition.getY()-1))
                    + spiralMemory.getValue(point(currentPosition.getX(), currentPosition.getY()+1))
                    + spiralMemory.getValue(point(currentPosition.getX()+1, currentPosition.getY()-1))
                    + spiralMemory.getValue(point(currentPosition.getX()+1, currentPosition.getY()))
                    + spiralMemory.getValue(point(currentPosition.getX()+1, currentPosition.getY()+1));
            spiralMemory.addNode(new MemoryNode(currentId, value));
        }
        return spiralMemory;
    }

    public int calculateManhattanDistanceFromNodeIdToAccessPort(int nodeId) {
        Point position = calculatePosition(nodeId);
        return manhattanDistanceToOrigin(position);
    }

    private int manhattanDistanceToOrigin(Point point){
        return Math.abs(point.getX()) + Math.abs(point.getY());
    }


}
