package designPT.singleton;

public final class HolderSingleton {

    private static final class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    private HolderSingleton() {
        System.out.println("HolderSingleton constructor");
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
