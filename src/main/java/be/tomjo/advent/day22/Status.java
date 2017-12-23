package be.tomjo.advent.day22;

import static be.tomjo.advent.Util.mod;

public enum Status {
    CLEAN, WEAKENED, INFECTED, FLAGGED;

    public Status next(){
        return values()[mod(ordinal() + 1, values().length)];
    }

    public Direction turn(Direction direction){
        switch (this){
            case CLEAN:
                return direction.turnLeft();
            case INFECTED:
                return direction.turnRight();
            case FLAGGED:
                return direction.reverse();
            case WEAKENED:
                return direction;
        }
        throw new IllegalStateException();
    }
}
