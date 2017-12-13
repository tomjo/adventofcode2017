package be.tomjo.advent.day1;

import java.io.IOException;
import java.util.List;

import static be.tomjo.advent.Util.readInput;
import static java.util.stream.Collectors.toList;

public class Captcha {


    public static void main(String[] args) throws IOException {
        Captcha captcha = new Captcha();

        String input = readInput("1.txt");
        List<Integer> digitList = captcha.toDigits(input);

        System.out.println("Solution 1.1: " + captcha.solveCaptchaWithNextDigitMatching(digitList));
        System.out.println("Solution 1.2: " + captcha.solveCaptchaWithHalfWayAroundDigitMatching(digitList));
    }

    public int solveCaptchaWithNextDigitMatching(List<Integer> digitList) {
        return solve(digitList, 1);
    }

    public int solveCaptchaWithHalfWayAroundDigitMatching(List<Integer> digitList) {
        return solve(digitList, digitList.size()/2);
    }

    private int solve(List<Integer> digitList, int searchOffset) {
        int sum = 0;
        for (int currentDigitIndex = 0; currentDigitIndex < digitList.size(); currentDigitIndex++) {
            int digit = digitList.get(currentDigitIndex);
            int nextDigit = calculateNextDigit(digitList, currentDigitIndex, searchOffset);
            if (nextDigit == digit) {
                sum += nextDigit;
            }
        }
        return sum;
    }

    private int calculateNextDigit(List<Integer> digitList, int currentDigitIndex, int searchOffset) {
        return digitList.get((currentDigitIndex + searchOffset) % digitList.size());
    }

    private List<Integer> toDigits(String digits) {
        return digits.chars().map(Character::getNumericValue).boxed().collect(toList());
    }
}
