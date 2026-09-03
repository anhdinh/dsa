package designPT.observer;

public class ObserverDemo {
    public static void main(String[] args) {
        NoteSubject noteSubject = new NoteSubject();
        noteSubject.addListener(new EmailService());

        noteSubject.addNote(new Note(1, "Mua sua"));
    }
}
