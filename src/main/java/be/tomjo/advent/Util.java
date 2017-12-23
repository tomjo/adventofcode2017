package be.tomjo.advent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

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

    public static <T> Set<T> of(Collection<T> ts, T... ts2){
        Set<T> set = new HashSet<>(ts);
        set.addAll(asList(ts2));
        return set;
    }

    public static <T> Set<T> merge(Collection<Set<T>> sets) {
        Set<T> merged = new HashSet<>();
        sets.forEach(merged::addAll);
        return merged;
    }

    public static int indexOf(int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value){
                return i;
            }
        }
        return -1;
    }

    public static int mod(int a, int b){
        return ((a % b) + b) % b;
    }

    public static <T> Result<T> benchmark(Supplier<T> runnable){
        long start = System.currentTimeMillis();
        T result = runnable.get();
        long end = System.currentTimeMillis();
        return new Result(result, end-start);
    }

    static class Result<T>{
        private final T result;
        private final long time;

        public Result(T result, long time) {
            this.result = result;
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public T getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "result=" + result +
                    ", time=" + time +
                    '}';
        }
    }
}
