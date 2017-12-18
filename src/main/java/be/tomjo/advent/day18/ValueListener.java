package be.tomjo.advent.day18;

public interface ValueListener<T> {
    void notify(T value);
}
