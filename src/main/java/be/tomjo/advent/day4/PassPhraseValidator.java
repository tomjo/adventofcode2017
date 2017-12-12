package be.tomjo.advent.day4;

import be.tomjo.advent.day1.Captcha;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Map;

import static java.lang.Character.valueOf;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PassPhraseValidator {

    public static void main(String[] args) throws IOException {
        String input = IOUtils.toString(Captcha.class.getClassLoader().getResourceAsStream("4.txt"), "UTF8");
        String[] passPhrases = input.split("\r\n");
        System.out.println("Solution 4.1: " + stream(passPhrases).filter(PassPhraseValidator::isValidPassPhrase1).count());
        System.out.println("Solution 4.2: " + stream(passPhrases).filter(PassPhraseValidator::isValidPassPhrase1).filter(PassPhraseValidator::isValidPassPhrase2).count());
    }


    public static boolean isValidPassPhrase1(String phrase) {
        String[] words = phrase.split(" ");
        return stream(words).distinct().count() == words.length;
    }

    public static boolean isValidPassPhrase2(String phrase) {
        String[] words = phrase.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isAnagram(words[i], words[j]))
                    return false;
            }
        }
        return true;
    }

    private static boolean isAnagram(String word, String otherWord) {
        return createHistogram(word).equals(createHistogram(otherWord));
    }

    private static Map<Character, Long> createHistogram(String word) {
        return word.chars().mapToObj(c -> valueOf((char) c)).collect(groupingBy(identity(), counting()));
    }
}
