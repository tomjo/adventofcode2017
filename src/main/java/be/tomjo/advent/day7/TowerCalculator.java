package be.tomjo.advent.day7;

import be.tomjo.advent.Util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class TowerCalculator {

    public static void main(String[] args) {
        String input = Util.readInput("7.txt");
        String[] towerRepresentations = input.split("\r\n");
        List<Tower> towers = createTowers(towerRepresentations);
        Tower bottomTower = findBottomTower(towers);
        System.out.println("Solution 7.1: " + bottomTower.getName());
        System.out.println("Solution 7.2: " + calculateWeightToBalanceAllTowers(towers, bottomTower));
    }

    public static int calculateWeightToBalanceAllTowers(List<Tower> towers, Tower bottomTower) {
        Map<String, Tower> towerMap = towers.stream().collect(toMap(Tower::getName, identity()));
        return calculateWeightToBalanceAllTowers(towerMap, bottomTower);
    }

    private static int calculateWeightToBalanceAllTowers(Map<String, Tower> towerMap, Tower bottomTower) {
        List<String> children = bottomTower.getChildren();
        int weightSum = children.stream().mapToInt(c -> getTowerWeight(towerMap, c)).sum();
        if (areChildrenBalanced(children, weightSum)) {
            return calculateNeededWeightToBalanceTower(towerMap, bottomTower);
        } else {
            Tower unbalancedTower = findUnbalancedTower(towerMap, children, weightSum);
            return calculateWeightToBalanceAllTowers(towerMap, unbalancedTower);
        }
    }

    private static Tower findUnbalancedTower(Map<String, Tower> towerMap, List<String> children, int weightSum) {
        return children.stream()
                .filter(towerName -> isUnevenTower(towerMap, children, weightSum, towerName))
                .findFirst()
                .map(towerMap::get)
                .orElseThrow(() -> new IllegalStateException("Balance check must be wrong"));
    }

    private static boolean isUnevenTower(Map<String, Tower> towerMap, List<String> children, int weightSum, String towerName) {
        return getTowerWeight(towerMap, towerName) != (weightSum / children.size()) - 1;
    }

    private static boolean areChildrenBalanced(List<String> children, int weightSum) {
        return (weightSum / children.size()) * children.size() == weightSum;
    }

    private static int calculateNeededWeightToBalanceTower(Map<String, Tower> towerMap, Tower bottomTower) {
        Tower parentTower = getParent(towerMap, bottomTower.getName());
        int neededWeightIncludingSubTowers = calculateNeededWeightToBalanceTowerIncludingSubTowers(towerMap, parentTower, bottomTower.getName());
        int weightDifference = neededWeightIncludingSubTowers - getTowerWeight(towerMap, bottomTower.getName());
        return bottomTower.getWeight() + weightDifference;
    }

    private static Integer calculateNeededWeightToBalanceTowerIncludingSubTowers(Map<String, Tower> towerMap, Tower parentTower, String unbalancedTowerName) {
        return parentTower.getChildren().stream()
                .filter(c -> !unbalancedTowerName.equals(c))
                .findAny()
                .map(t -> getTowerWeight(towerMap, t))
                .orElseThrow(() -> new IllegalStateException("Parent node has only 1 child"));
    }

    private static Tower getParent(Map<String, Tower> towerMap, String towerName) {
        return towerMap.values().stream()
                .filter(t -> t.getChildren().contains(towerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No parent for " + towerName));
    }

    private static int getTowerWeight(Map<String, Tower> towerMap, String towerName) {
        Tower tower = towerMap.get(towerName);
        return tower.getWeight() + tower.getChildren().stream()
                .mapToInt(n -> getTowerWeight(towerMap, n))
                .sum();
    }

    public static Tower findBottomTower(List<Tower> towers) {
        List<String> towerNames = towers.stream()
                .map(Tower::getName)
                .collect(toList());
        towerNames.removeAll(findNonBottomTowers(towers));
        return towers.stream()
                .filter(t -> t.getName().equals(towerNames.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No bottom tower found"));
    }

    private static List<String> findNonBottomTowers(List<Tower> towers) {
        return towers.stream()
                .map(Tower::getChildren)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    public static List<Tower> createTowers(String[] towerRepresentations) {
        return stream(towerRepresentations)
                .map(Tower::new)
                .collect(toList());
    }
}
