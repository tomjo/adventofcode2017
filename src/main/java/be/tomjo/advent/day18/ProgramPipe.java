package be.tomjo.advent.day18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProgramPipe<T> {

    private static final int TIMEOUT = 10;
    private final ArrayBlockingQueue<T> queue;

    private final Collection<ValueListener> sendListeners;
    private final Collection<ValueListener> receiveListeners;

    public ProgramPipe(){
        this.queue = new ArrayBlockingQueue<>(1000);
        this.sendListeners = new ArrayList<>();
        this.receiveListeners = new ArrayList<>();
    }

    public T poll(){
        try {
            T result = queue.poll(TIMEOUT, TimeUnit.SECONDS);
            if(result == null){
                throw new RuntimeException(new TimeoutException());
            }
            receiveListeners.forEach(l -> l.notify(result));
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(new TimeoutException());
        }
    }

    public void add(T t){
        sendListeners.forEach(l -> l.notify(t));
        queue.add(t);
    }

    public void addSendListener(ValueListener listener) {
        sendListeners.add(listener);
    }

    public void addReceiveListener(ValueListener listener) {
        receiveListeners.add(listener);
    }
}
