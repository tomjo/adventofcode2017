package be.tomjo.advent.day9;

import be.tomjo.advent.Util;

import java.util.ArrayDeque;
import java.util.Deque;

public class Groups {

    public static void main(String[] args) {
        String stream = Util.readInput("9.txt");
        StreamProcessResult result = process(stream);
        System.out.println("Solution 9.1: "+ result.getScore());
        System.out.println("Solution 9.2: "+ result.getGarbage());
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
                }else{
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
