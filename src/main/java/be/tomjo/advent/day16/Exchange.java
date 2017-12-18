package be.tomjo.advent.day16;

import java.util.List;

public class Exchange implements DanceMove {

    private final int position1;
    private final int position2;

    public Exchange(int position1, int position2) {
        this.position1 = position1;
        this.position2 = position2;
    }

    @Override
    public void dance(List<Character> programs) {
        char temp = programs.get(position1);
        programs.set(position1, programs.get(position2));
        programs.set(position2, temp);
    }
}

