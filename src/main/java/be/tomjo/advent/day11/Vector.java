package be.tomjo.advent.day11;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class Vector {

    public static Vector IDENTITY = vector(0, 0, 0);

    private static final Map<String, Vector> DIRECTION_MAP;

    static {
        DIRECTION_MAP = new HashMap<>();
        DIRECTION_MAP.put("n", vector(-1, 0, 1));
        DIRECTION_MAP.put("ne", vector(-1, 1, 0));
        DIRECTION_MAP.put("se", vector(0, 1, -1));
        DIRECTION_MAP.put("s", vector(1, 0, -1));
        DIRECTION_MAP.put("sw", vector(1, -1, 0));
        DIRECTION_MAP.put("nw", vector(0, -1, 1));
    }

    private final int x;
    private final int y;
    private final int z;

    public static Vector direction(String direction) {
        return DIRECTION_MAP.get(direction);
    }

    public static Vector vector(int x, int y, int z) {
        return new Vector(x, y, z);
    }

    private Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Vector add(Vector v) {
        return vector(x + v.x, y + v.y, z + v.z);
    }

    public int manhattanDistanceToOrigin(){
        return abs(x) + abs(y) + abs(z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (x != vector.x) return false;
        if (y != vector.y) return false;
        return z == vector.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
