package be.tomjo.advent.day3;

import static be.tomjo.advent.day3.Point.point;

public class SpiralMemoryPositionCalculator {

    public static Point calculatePosition(int nodeId) {
        int spiralDiameter = calculateSpiralDiameter(nodeId);
        int lastNodeIdOfPreviousSpiral = calculateLastNodeIdFromSpiral(spiralDiameter);
        int stepsInCurrentSpiral = nodeId - lastNodeIdOfPreviousSpiral;

        if (inRightSideOfCurrentSpiral(spiralDiameter, stepsInCurrentSpiral)) {//RIGHT SIDE e.g. 10, 11, 12, 13
            return point(spiralDiameter / 2, -(spiralDiameter / 2) + stepsInCurrentSpiral);
        } else if (inTopSideOfCurrentSpiral(spiralDiameter, stepsInCurrentSpiral)) {//TOP e.g. 14, 15, 16, 17
            return point(spiralDiameter / 2 - (stepsInCurrentSpiral - (spiralDiameter - 1)), spiralDiameter / 2);
        } else if (inLeftSideOfCurrentSpiral(spiralDiameter, stepsInCurrentSpiral)) {
            return point(-(spiralDiameter / 2), spiralDiameter / 2 - (stepsInCurrentSpiral - (2 * (spiralDiameter - 1))));
        } else {
            return point(-(spiralDiameter / 2) + (stepsInCurrentSpiral - ((spiralDiameter - 1) * 3)), -(spiralDiameter / 2));
        }
    }

    private static int calculateLastNodeIdFromSpiral(int spiralDiameter) {
        int previousSpiralDiameter = spiralDiameter - 2;
        return previousSpiralDiameter * previousSpiralDiameter;
    }

    private static int calculateSpiralDiameter(int nodeId) {
        int spiralDiameter = (int) Math.ceil(Math.sqrt(nodeId));
        if (spiralDiameter % 2 == 0) {
            spiralDiameter++;
        }
        return spiralDiameter;
    }

    private static boolean inLeftSideOfCurrentSpiral(int spiralDiameter, int stepsInCurrentSpiral) {
        return (stepsInCurrentSpiral >= (spiralDiameter * 2) - 1) && (stepsInCurrentSpiral <= (spiralDiameter - 1) * 3);
    }

    private static boolean inTopSideOfCurrentSpiral(int spiralDiameter, int stepsInCurrentSpiral) {
        return (stepsInCurrentSpiral >= spiralDiameter) && stepsInCurrentSpiral <= (spiralDiameter - 1) * 2;
    }

    private static boolean inRightSideOfCurrentSpiral(int spiralDiameter, int stepsInCurrentSpiral) {
        return stepsInCurrentSpiral < spiralDiameter;
    }
}
