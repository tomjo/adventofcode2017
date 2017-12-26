package be.tomjo.advent;

import be.tomjo.advent.Util.Result;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Math.max;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.reflections.util.ClasspathHelper.contextClassLoader;
import static org.reflections.util.ClasspathHelper.forClassLoader;
import static org.reflections.util.ClasspathHelper.staticClassLoader;
import static org.reflections.util.FilterBuilder.prefix;

public class Overview {

    private static final LocalDate XMAS = of(2017, 12, 25);

    public static void main(String[] args) {
        rangeClosed(1, calculateDaysToShow()).forEach(day -> {
            List<Result> results = executeDay(day);
            range(0, results.size())
                    .forEach(part -> printSolution(results, day, part));
        });
    }

    private static void printSolution(List<Result> results, int day, int part) {
        System.out.println("Solution " + day + "." + (part + 1) + ": " + results.get(part).getResult() + " | Took " + results.get(part).getTime() + " ms.");
    }

    private static int calculateDaysToShow() {
        int dayDifference = (int) DAYS.between(now(), XMAS);
        return 25 - max(0, dayDifference);
    }

    public static List<Result> executeDay(int day) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false))
                .setUrls(forClassLoader(contextClassLoader(), staticClassLoader()))
                .filterInputsBy(new FilterBuilder().include(prefix(getDayPackageName(day)))));

        String input = readInput(day + ".txt");
        return reflections.getSubTypesOf(Object.class).stream()
                .filter(Overview::hasMainMethod)
                .map(Overview::getPartMethods)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .map(m -> benchmark(() -> execute(m, input)))
                .collect(toList());
    }

    private static String getDayPackageName(int day) {
        String className = Overview.class.getName();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        return packageName + ".day" + day + ".";
    }

    private static Method[] getPartMethods(Class<?> mainClass) {
        Method[] parts = new Method[2];
        try {
            parts[0] = mainClass.getMethod("part1", String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            parts[1] = mainClass.getMethod("part2", String.class);
        } catch (NoSuchMethodException e) {
        }
        return parts;
    }

    private static boolean hasMainMethod(Class<?> c) {
        try {
            c.getMethod("main", String[].class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private static Object execute(Method method, String input) {
        try {
            return method.invoke(null, input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
