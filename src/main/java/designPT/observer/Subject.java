package designPT.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners(Note note) {
        for (Listener listener : listeners) {
            listener.notifyData(note);
        }
    }
}
