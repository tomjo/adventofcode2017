package be.tomjo.advent.day18;

public class LastValueRecordingListener implements ValueListener<Long> {

    private long lastReceivedValue;

    @Override
    public void notify(Long v) {
        lastReceivedValue = v;
    }

    public long getLastReceivedValue() {
        return lastReceivedValue;
    }
}
