package be.tomjo.advent.day18;

import java.util.*;
import java.util.concurrent.TimeoutException;

public class ProgramPipe<T> {

    private final Deque<T> queue;

    private final Collection<ValueListener> sendListeners;
    private final Collection<ValueListener> receiveListeners;

    public ProgramPipe() {
        this.queue = new ArrayDeque<>(1000);
        this.sendListeners = new ArrayList<>();
        this.receiveListeners = new ArrayList<>();
    }

    public T poll() throws TimeoutException {
        try {
            T result = queue.removeFirst();
            receiveListeners.forEach(l -> l.notify(result));
            return result;
        }catch(NoSuchElementException e){
            throw new TimeoutException();
        }
    }

    public void add(T t) {
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
