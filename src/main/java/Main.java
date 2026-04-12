public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){}
                }
            }).start();
        }
    }
}
