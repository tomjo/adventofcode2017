package be.tomjo.advent.day12;

import be.tomjo.advent.Util;

import java.util.*;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Pipes {

    public static void main(String[] args) {
        String input = readInput("12.txt");
        System.out.println("Solution 12.1: " + benchmark(()->part1(input)));
        System.out.println("Solution 12.2: " + benchmark(()->part2(input)));
    }

    public static int part1(String input){
        Collection<Set<String>> graph = getConnectedProgramSets(asList(input.split("\r\n")));
        return getConnectedComponents(graph, "0").size();
    }

    public static int part2(String input){
        Collection<Set<String>> graph = getConnectedProgramSets(asList(input.split("\r\n")));
        return graph.size();
    }

    public static Collection<Set<String>> getConnectedProgramSets(Collection<String> inputRecords) {
        List<Set<String>> connectedProgramSets = new ArrayList<>();
        for (String inputRecord : inputRecords) {
            String[] parts = inputRecord.split(" <-> ");
            Set<String> programs = Util.of(asList(parts[1].split(", ")), parts[0]);
            addProgramsToConnectedSet(connectedProgramSets, programs);
        }
        return connectedProgramSets;
    }

    private static void addProgramsToConnectedSet(List<Set<String>> connectedProgramSets, Set<String> programs) {
        List<Set<String>> sets;
        do {
            sets = findSetsContainingAnyProgram(connectedProgramSets, programs);
            if (sets.isEmpty()) {
                connectedProgramSets.add(programs);
            } else if (sets.size() == 1) {
                sets.get(0).addAll(programs);
            } else {
                mergeConnectedProgramSets(connectedProgramSets, sets);
            }
        } while (sets.size() != 1);
    }

    private static List<Set<String>> findSetsContainingAnyProgram(Collection<Set<String>> collection, Collection<String> programs) {
        return collection.stream()
                .filter(set -> programs.stream().anyMatch(set::contains))
                .collect(toList());
    }

    private static void mergeConnectedProgramSets(List<Set<String>> connectedProgramSets, List<Set<String>> sets) {
        connectedProgramSets.removeAll(sets);
        Set<String> set = new HashSet<>();
        sets.forEach(set::addAll);
        connectedProgramSets.add(set);
    }

    private static Collection<String> getConnectedComponents(Collection<Set<String>> connectedProgramSets, String id){
        return connectedProgramSets.stream()
                .filter(set -> set.contains(id))
                .findAny()
                .orElse(of(id));
    }

}
