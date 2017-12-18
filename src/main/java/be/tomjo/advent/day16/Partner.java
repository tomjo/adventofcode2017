package be.tomjo.advent.day16;

import java.util.List;

public class Partner implements DanceMove {

    private final char program1;
    private final char program2;

    public Partner(char program1, char program2) {
        this.program1 = program1;
        this.program2 = program2;
    }

    @Override
    public void dance(List<Character> programs) {
        int position1 = programs.indexOf(program1);
        int position2 = programs.indexOf(program2);
        new Exchange(position1, position2).dance(programs);
    }
}
