package be.tomjo.advent.day9;

import java.util.ArrayDeque;
import java.util.Deque;

import static be.tomjo.advent.Util.benchmark;
import static be.tomjo.advent.Util.readInput;

public class Groups {

    public static void main(String[] args) {
        String input = readInput("9.txt");
        System.out.println("Solution 9.1: " + benchmark(() -> part1(input)));
        System.out.println("Solution 9.2: " + benchmark(() -> part2(input)));
    }

    public static int part1(String input) {
        return process(input).getScore();
    }

    public static int part2(String input) {
        return process(input).getGarbage();
    }

    public static StreamProcessResult process(String stream) {
        Deque<Character> stack = new ArrayDeque<>();
        int score = 0;
        int garbage = 0;
        for (char c : stream.toCharArray()) {
            char last = stack.isEmpty() ? '$' : stack.peek();
            if (last == '!') {
                stack.pop();
            } else if (c == '!') {
                stack.push(c);
            } else if (last == '<') {
                if (c == '>') {
                    stack.pop();
                } else {
                    garbage++;
                }
            } else {
                if (c == '<' || c == '{') {
                    stack.push(c);
                } else if (c == '}') {
                    stack.pop();
                    score += 1 + countParentGroups(stack);
                }
            }
        }
        return new StreamProcessResult(score, garbage);
    }

    private static int countParentGroups(Deque<Character> stack) {
        return (int) stack.stream().filter(ch -> ch.equals('{')).count();
    }

    static class StreamProcessResult {
        private final int score;
        private final int garbage;

        public StreamProcessResult(int score, int garbage) {
            this.score = score;
            this.garbage = garbage;
        }

        public int getGarbage() {
            return garbage;
        }

        public int getScore() {
            return score;
        }
    }
}
