package be.tomjo.advent.day18;

public class CountingValueListener implements ValueListener<Long> {
    private long count = 0;

    @Override
    public void notify(Long value) {
        count++;
    }

    public long getCount() {
        return count;
    }
}
