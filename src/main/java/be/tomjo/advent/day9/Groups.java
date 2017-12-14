package be.tomjo.advent.day9;

import be.tomjo.advent.Util;

import java.util.ArrayDeque;
import java.util.Deque;

public class Groups {

    public static void main(String[] args) {
        String stream = Util.readInput("9.txt");
        System.out.println("Solution 9.1: "+score(stream));
    }

    public static int score(String stream) {
        Deque<Character> stack = new ArrayDeque<>();
        int score = 0;
        for (char c : stream.toCharArray()) {
            char last = stack.isEmpty() ? '$' : stack.peek();
            if (last == '!') {
                stack.pop();
            } else if (c == '!') {
                stack.push(c);
            } else if (last == '<') {
                if (c == '>') {
                    stack.pop();
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
        return score;
    }

    private static int countParentGroups(Deque<Character> stack) {
        return (int) stack.stream().filter(ch -> ch.equals('{')).count();
    }
}
