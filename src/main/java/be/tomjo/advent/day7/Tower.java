package be.tomjo.advent.day7;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Tower {

    private final String name;
    private final int weight;
    private final List<String> children;

    public Tower(String representation) {
        String[] parts = representation.split(" -> ");
        String[] subParts = parts[0].split(" \\(");
        this.name = subParts[0];
        this.weight = Integer.parseInt(subParts[1].substring(0, subParts[1].indexOf(")")));
        this.children = parts.length == 2 ? asList(parts[1].split(", ")) : Collections.emptyList();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getChildren() {
        return children;
    }
}
