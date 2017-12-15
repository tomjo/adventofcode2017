package be.tomjo.advent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;

public class Util {

    public static String readInput(String name){
        try {
            return IOUtils.toString(Util.class.getClassLoader().getResourceAsStream(name), "UTF8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] concat(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
