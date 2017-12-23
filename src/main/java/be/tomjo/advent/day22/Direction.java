package be.tomjo.advent.day22;

import static be.tomjo.advent.Util.mod;

public enum Direction {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction turnRight() {
        return Direction.values()[mod(ordinal() + 1, Direction.values().length)];
    }

    public Direction turnLeft() {
        return values()[mod(ordinal() - 1, values().length)];
    }

    public Direction reverse() {
        return values()[mod(ordinal() + 2, values().length)];
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
