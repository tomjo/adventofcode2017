package be.tomjo.advent.day20;

import be.tomjo.advent.day11.Vector;

import java.util.*;
import java.util.stream.Collectors;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Math.abs;
import static java.lang.Math.signum;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

public class Particles {

    public static void main(String[] args) {
        String input = readInput("20.txt");
        System.out.println("Solution 20.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 20.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input) {
        List<Particle> particles = toParticles(input);
        List<Particle> candidates = findParticlesWithMinimumAbsoluteAcceleration(particles);
        int closestParticle = particles.indexOf(findClosestParticle(candidates));
        int threshold = 50;
        int iterationsWithSameClosestParticle = 0;
        while(iterationsWithSameClosestParticle < threshold) {
            candidates.forEach(Particle::tick);
            int newClosestParticle = particles.indexOf(findClosestParticle(candidates));
            if(newClosestParticle != closestParticle){
                iterationsWithSameClosestParticle = 0;
                closestParticle = newClosestParticle;
            }else{
                iterationsWithSameClosestParticle++;
            }
        }
        return closestParticle;
    }

    private static Particle findClosestParticle(List<Particle> candidates) {
        return candidates.stream()
                .min(comparingInt(i -> i.getPosition().manhattanDistanceToOrigin()))
                .orElseThrow(IllegalStateException::new);
    }

    private static List<Particle> findParticlesWithMinimumAbsoluteAcceleration(List<Particle> particles) {
        int minimumAbsoluteAcceleration = particles.stream()
                .map(Particle::getAcceleration)
                .mapToInt(Vector::manhattanDistanceToOrigin)
                .map(Math::abs)
                .min()
                .orElseThrow(IllegalStateException::new);
        return particles.stream()
                .filter(p -> abs(p.getAcceleration().manhattanDistanceToOrigin()) == minimumAbsoluteAcceleration)
                .collect(toList());
    }


    public static int part2(String input) {
        int threshold = 10;
        List<Particle> particles = toParticles(input);
        int iterationsWithoutCollision = 0;
        while (iterationsWithoutCollision < threshold) {
            particles.forEach(Particle::tick);
            if (removeParticlesWithDuplicatePosition(particles)) {
                iterationsWithoutCollision = 0;
            } else {
                iterationsWithoutCollision++;
            }
        }
        return particles.size();
    }

    private static boolean removeParticlesWithDuplicatePosition(List<Particle> particles) {
        Set<Vector> collisionPositions = findParticleCollisionPositions(particles);
        return particles.removeIf(particle -> collisionPositions.contains(particle.getPosition()));
    }

    private static Set<Vector> findParticleCollisionPositions(List<Particle> particles) {
        Set<Vector> positions = new HashSet<>();
        return particles.stream()
                .filter(particle -> !positions.add(particle.getPosition()))
                .map(Particle::getPosition)
                .collect(toSet());
    }

    private static List<Particle> toParticles(String input) {
        return stream(input.split("\r\n")).map(Particle::new).collect(toList());
    }
}
