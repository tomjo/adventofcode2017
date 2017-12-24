package be.tomjo.advent.day24;

import be.tomjo.advent.Util;

import java.util.*;

import static be.tomjo.advent.Util.benchmark;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.max;
import static java.util.Comparator.comparingInt;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Bridge {

    public static final Comparator<BridgeValues> BRIDGE_VALUES_COMPARATOR = comparingInt(BridgeValues::getLength).thenComparingInt(BridgeValues::getStrength);

    public static void main(String[] args) {
        String input = Util.readInput("24.txt");
        System.out.println("Solution 24.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 24.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input) {
        Map<Component, Boolean> components = createUsedComponentsMap(input);
        return computeBridgeWithHighestStrength(components, 0);
    }

    public static int part2(String input) {
        Map<Component, Boolean> components = createUsedComponentsMap(input);
        return computeLongestBridgeWithHighestStrength(components, 0).getStrength();
    }

    private static int computeBridgeWithHighestStrength(Map<Component, Boolean> components, int currentPort) {
        List<Component> next = getUnusedComponentsWithPort(components, currentPort);
        int maxStrength = 0;
        for (Component component : next) {
            components.put(component, true);
            maxStrength = Math.max(maxStrength, component.getStrength() + computeBridgeWithHighestStrength(components, component.getOtherPort(currentPort)));
            components.put(component, false);
        }
        return maxStrength;
    }

    private static BridgeValues computeLongestBridgeWithHighestStrength(Map<Component, Boolean> components, int currentPort) {
        List<Component> next = getUnusedComponentsWithPort(components, currentPort);
        BridgeValues bridgeValues = new BridgeValues(0, 0);
        for (Component component : next) {
            components.put(component, true);
            BridgeValues otherBridgeValues = computeLongestBridgeWithHighestStrength(components, component.getOtherPort(currentPort))
                    .add(component.getStrength(), 1);
            bridgeValues = max(asList(bridgeValues, otherBridgeValues), BRIDGE_VALUES_COMPARATOR);
            components.put(component, false);
        }
        return bridgeValues;
    }

    private static List<Component> getUnusedComponentsWithPort(Map<Component, Boolean> components, int currentPort) {
        return components.entrySet()
                .stream()
                .filter(e -> !e.getValue())
                .map(Map.Entry::getKey)
                .filter(c -> c.getPort1() == currentPort || c.getPort2() == currentPort)
                .collect(toList());
    }

    private static Map<Component, Boolean> createUsedComponentsMap(String input) {
        return stream(input.split("\r\n")).map(Component::new).collect(toMap(identity(), c -> false));
    }
}
