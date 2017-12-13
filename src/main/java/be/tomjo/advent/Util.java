package be.tomjo.advent;

import be.tomjo.advent.day1.Captcha;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class Util {

    public static String readInput(String name){
        try {
            return IOUtils.toString(Util.class.getClassLoader().getResourceAsStream(name), "UTF8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
