package designPT.singleton;

public final class DoubleCheckedSingleton {

    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {
        System.out.println("DoubleCheckedSingleton constructor");
    }

    public static DoubleCheckedSingleton getInstance() {
        DoubleCheckedSingleton current = instance;
        if (current == null) {
            synchronized (DoubleCheckedSingleton.class) {
                current = instance;
                if (current == null) {
                    current = new DoubleCheckedSingleton();
                    instance = current;
                }
            }
        }
        return current;
    }
}
