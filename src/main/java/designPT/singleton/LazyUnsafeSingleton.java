package designPT.singleton;

public final class LazyUnsafeSingleton {

    private static LazyUnsafeSingleton instance;

    private LazyUnsafeSingleton() {
        System.out.println("LazyUnsafeSingleton constructor");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static LazyUnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazyUnsafeSingleton();
        }
        return instance;
    }
}
