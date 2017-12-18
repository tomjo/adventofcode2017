package be.tomjo.advent.day16;

import java.util.List;

import static java.util.Collections.rotate;

public class Spin implements DanceMove {

    private final int value;

    public Spin(int value) {
        this.value = value;
    }

    @Override
    public void dance(List<Character> programs) {
        rotate(programs, value);
    }
}
