package designPT.singleton;

public final class EagerSingleton {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("EagerSingleton constructor");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
