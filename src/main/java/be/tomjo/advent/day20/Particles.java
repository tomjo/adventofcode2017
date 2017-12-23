package be.tomjo.advent.day20;

import be.tomjo.advent.day11.Vector;

import java.util.*;
import java.util.stream.Collectors;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

public class Particles {

    public static void main(String[] args) {
        String input = readInput("20.txt");
        System.out.println("Solution 20.1: "+benchmark(()->part1(input)));
        System.out.println("Solution 20.2: "+benchmark(()->part2(input)));
    }

    public static int part1(String input){
        List<Particle> particles = toParticles(input);
        Map<Integer,Integer> timesClosest = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            particles.forEach(Particle::tick);
            int closestParticle = getClosestParticle(particles);
            timesClosest.put(closestParticle, timesClosest.getOrDefault(closestParticle, 0)+1);
        }
        return timesClosest.entrySet().stream().max(comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).orElseThrow(IllegalStateException::new);
    }

    public static int part2(String input){
        List<Particle> particles = toParticles(input);
        for (int i = 0; i < 10000; i++) {
            particles.forEach(Particle::tick);
            removeParticlesWithDuplicatePosition(particles);
        }
        return particles.size();
    }

    private static void removeParticlesWithDuplicatePosition(List<Particle> particles) {
        Set<Vector> collisionPositions = findParticleCollisionPositions(particles);
        particles.removeIf(particle -> collisionPositions.contains(particle.getPosition()));
    }

    private static Set<Vector> findParticleCollisionPositions(List<Particle> particles) {
        Set<Vector> positions = new HashSet<>();
        return particles.stream()
                .filter(particle -> !positions.add(particle.getPosition()))
                .map(Particle::getPosition)
                .collect(toSet());
    }

    private static List<Particle> toParticles(String input) {
        return stream(input.split("\r\n")).map(Particle::new).collect(Collectors.toList());
    }

    private static int getClosestParticle(List<Particle> particles) {
        return range(0, particles.size())
                .boxed()
                .min(comparingInt(i -> particles.get(i).getPosition().manhattanDistanceToOrigin()))
                .orElseThrow(IllegalStateException::new);
    }
}
