package be.tomjo.advent.day15;

import java.util.function.LongPredicate;

public class Generator {
    private long previousValue;
    private final long factor;
    private final long divisor;
    private final LongPredicate criteria;

    public Generator(long seed, long factor, long divisor){
        this(seed, factor, divisor, l-> true);
    }

    public Generator(long seed, long factor, long divisor, LongPredicate criteria){
        this.previousValue = seed;
        this.factor = factor;
        this.divisor = divisor;
        this.criteria = criteria;
    }

    public long generate(){
        previousValue = calculateNextValue();
        while(!criteria.test(previousValue)){
            previousValue = calculateNextValue();
        }
        return previousValue;
    }

    private long calculateNextValue() {
        return (previousValue * factor) % divisor;
    }
}
