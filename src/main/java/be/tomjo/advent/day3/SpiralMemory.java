package be.tomjo.advent.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static be.tomjo.advent.day3.SpiralMemoryPositionCalculator.calculatePosition;

public class SpiralMemory {

    private List<MemoryNode> memoryNodes;

    public SpiralMemory(){
        memoryNodes = new ArrayList<>();
    }

    public int getValue(Point position){
        return memoryNodes.stream()
                .filter(n -> calculatePosition(n.getNodeId()).equals(position))
                .findAny()
                .map(MemoryNode::getValue)
                .orElse(0);
    }

    public void addNode(MemoryNode memoryNode){
        memoryNodes.add(memoryNode);
    }

    public Stream<MemoryNode> stream() {
        return memoryNodes.stream();
    }
}
