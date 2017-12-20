package be.tomjo.advent.day20;

import be.tomjo.advent.day11.Vector;

import static be.tomjo.advent.day11.Vector.vector;
import static java.util.Arrays.stream;

public class Particle {

    private Vector position;
    private Vector velocity;
    private Vector acceleration;

    public Particle(String serialized){
        String[] parts = serialized.split(", ");
        this.position = createVector(parts[0]);
        this.velocity = createVector(parts[1]);
        this.acceleration = createVector(parts[2]);
    }

    private static Vector createVector(String part) {
        String coordsSerialized = part.substring(3, part.length() - 1);
        int[] coords = stream(coordsSerialized.split(",")).mapToInt(Integer::parseInt).toArray();
        return vector(coords[0], coords[1], coords[2]);
    }

    public void tick(){
        velocity = velocity.add(acceleration);
        position = position.add(velocity);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        if (!position.equals(particle.position)) return false;
        if (!velocity.equals(particle.velocity)) return false;
        return acceleration.equals(particle.acceleration);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + velocity.hashCode();
        result = 31 * result + acceleration.hashCode();
        return result;
    }
}
