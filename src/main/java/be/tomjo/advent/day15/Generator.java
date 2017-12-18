package be.tomjo.advent.day15;

public class Generator {
    private long previousValue;
    private final long factor;
    private final long divisor;

    public Generator(long seed, long factor, long divisor){
        this.previousValue = seed;
        this.factor = factor;
        this.divisor = divisor;
    }

    public long generate(){
        return previousValue = calculateNextValue();
    }

    private long calculateNextValue() {
        return (previousValue * factor) % divisor;
    }
}
