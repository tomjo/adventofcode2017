package be.tomjo.advent.day4;

import java.io.IOException;
import java.util.Map;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;
import static java.lang.Character.valueOf;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PassPhraseValidator {

    public static void main(String[] args) throws IOException {
        String input = readInput("4.txt");
        System.out.println("Solution 4.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 4.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input){
        return (int) stream(input.split("\r\n"))
                .filter(PassPhraseValidator::isValidPassPhrase1)
                .count();
    }

    public static int part2(String input){
        return (int) stream(input.split("\r\n"))
                .filter(PassPhraseValidator::isValidPassPhrase1)
                .filter(PassPhraseValidator::isValidPassPhrase2)
                .count();
    }

    public static boolean isValidPassPhrase1(String phrase) {
        String[] words = phrase.split(" ");
        return stream(words).distinct().count() == words.length;
    }

    public static boolean isValidPassPhrase2(String phrase) {
        String[] words = phrase.split(" ");
        return stream(words).map(PassPhraseValidator::createHistogram).distinct().count() == words.length;
    }

    private static Map<Character, Long> createHistogram(String word) {
        return word.chars().mapToObj(c -> valueOf((char) c)).collect(groupingBy(identity(), counting()));
    }
}
