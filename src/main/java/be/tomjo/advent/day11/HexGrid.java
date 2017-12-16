package be.tomjo.advent.day11;

import be.tomjo.advent.Util;
import com.google.common.primitives.Ints;

import static be.tomjo.advent.day11.Vector.IDENTITY;
import static java.util.Arrays.stream;

public class HexGrid {

    public static void main(String[] args) {
        String input = Util.readInput("11.txt").replace("\r\n", "");

        System.out.println("Solution 11.1: "+fewestStepsToEndDestination(input));
    }

    public static int fewestStepsToEndDestination(String inputDirections){
        Vector direction = stream(inputDirections.split(",")).map(Vector::direction).reduce(IDENTITY, Vector::add);
        return Ints.max(direction.getX(), direction.getY(), direction.getZ());
    }

}
