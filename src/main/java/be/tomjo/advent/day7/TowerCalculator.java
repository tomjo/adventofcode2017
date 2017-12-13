package be.tomjo.advent.day7;

import be.tomjo.advent.Util;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class TowerCalculator {

    public static void main(String[] args) {
        String input = Util.readInput("7.txt");
        String[] towerRepresentations = input.split("\r\n");
        Tower bottomTower = findBottomTower(towerRepresentations);
        System.out.println("Solution 7.1: "+bottomTower.getName());
    }

    public static Tower findBottomTower(String[] towerRepresentations) {
        List<Tower> towers = createTowers(towerRepresentations);
        List<String> towerNames = towers.stream().map(Tower::getName).collect(toList());
        towerNames.removeAll(findNonBottomTowers(towers));
        return towers.stream().filter(t -> t.getName().equals(towerNames.get(0))).findFirst().orElseThrow(() -> new IllegalStateException("No bottom tower found"));
    }

    private static List<String> findNonBottomTowers(List<Tower> towers) {
        return towers.stream().map(Tower::getChildren).flatMap(Collection::stream).collect(toList());
    }

    private static List<Tower> createTowers(String[] towerRepresentations) {
        return stream(towerRepresentations).map(Tower::new).collect(toList());
    }
}
