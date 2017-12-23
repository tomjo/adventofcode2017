package be.tomjo.advent.day21;

import java.util.Collection;
import java.util.HashSet;

import static be.tomjo.advent.day21.Art.horizontalFlip;
import static be.tomjo.advent.day21.Art.rotate90CW;

public class Rule {

    private final String input;
    private final String output;

    public Rule(String serialized) {
        String[] parts = serialized.split(" => ");
        this.input = parts[0];
        this.output = parts[1];
    }

    public Rule(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public Collection<Rule> getVariations() {
        Collection<Rule> variations = new HashSet<>();
        variations.add(this);
        variations.add(new Rule(rotate90CW(getInput()), output));
        variations.add(new Rule(rotate90CW(rotate90CW(getInput())), output));
        variations.add(new Rule(rotate90CW(rotate90CW(rotate90CW(getInput()))), output));
        variations.add(new Rule(horizontalFlip(getInput()), output));
        variations.add(new Rule(horizontalFlip(rotate90CW(getInput())), output));
        variations.add(new Rule(horizontalFlip(rotate90CW(rotate90CW(getInput()))), output));
        variations.add(new Rule(horizontalFlip(rotate90CW(rotate90CW(rotate90CW(getInput())))), output));
        return variations;
    }

    public String getOutput() {
        return output;
    }

    public String getInput() {
        return input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        return input.equals(rule.input);
    }

    @Override
    public int hashCode() {
        return input.hashCode();
    }

    @Override
    public String toString() {
        return "Rule{" +
                "input='" + input + '\'' +
                '}';
    }
}
