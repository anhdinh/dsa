package designPT.observer;

public class EmailService implements Listener {

    @Override
    public void notifyData(Note note) {
        System.out.println("Email sent for note: " + note.content());
    }
}
