package designPT.observer;

import java.util.ArrayList;
import java.util.List;

public class NoteSubject extends Subject {
    private final List<Note> db = new ArrayList<>();

    public void addNote(Note note) {
        db.add(note);
        System.out.println("Saved note to DB: " + note);

        notifyListeners(note);
    }
}
