package be.tomjo.advent.day3;

public class MemoryNode {

    private final int nodeId;
    private int value;

    public MemoryNode(int nodeId, int value){
        this.nodeId = nodeId;
        this.value = value;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
