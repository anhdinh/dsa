package designPT.singleton;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class SingletonDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- eager ---");
        EagerSingleton e1 = EagerSingleton.getInstance();
        EagerSingleton e2 = EagerSingleton.getInstance();
        System.out.println("e1 == e2 : " + (e1 == e2));

        System.out.println("--- lazy unsafe : race on first-ever call ---");
        int unsafeDistinct = distinctAfterRace(LazyUnsafeSingleton::getInstance);
        System.out.println("lazy unsafe distinct instances : " + unsafeDistinct);
        LazyUnsafeSingleton u1 = LazyUnsafeSingleton.getInstance();
        LazyUnsafeSingleton u2 = LazyUnsafeSingleton.getInstance();
        System.out.println("after race, u1 == u2 : " + (u1 == u2));

        System.out.println("--- synchronized ---");
        SynchronizedSingleton s1 = SynchronizedSingleton.getInstance();
        SynchronizedSingleton s2 = SynchronizedSingleton.getInstance();
        System.out.println("s1 == s2 : " + (s1 == s2));

        System.out.println("--- double checked locking ---");
        DoubleCheckedSingleton d1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton d2 = DoubleCheckedSingleton.getInstance();
        System.out.println("d1 == d2 : " + (d1 == d2));

        System.out.println("--- holder ---");
        HolderSingleton h1 = HolderSingleton.getInstance();
        HolderSingleton h2 = HolderSingleton.getInstance();
        System.out.println("h1 == h2 : " + (h1 == h2));

        System.out.println("--- enum ---");
        EnumSingleton x1 = EnumSingleton.INSTANCE;
        EnumSingleton x2 = EnumSingleton.INSTANCE;
        System.out.println("x1 == x2 : " + (x1 == x2));

        System.out.println("--- race on first-ever call (others) ---");
        System.out.println("double checked distinct instances : " + distinctAfterRace(DoubleCheckedSingleton::getInstance));
        System.out.println("holder distinct instances : " + distinctAfterRace(HolderSingleton::getInstance));
        System.out.println("enum distinct instances : " + distinctAfterRace(() -> EnumSingleton.INSTANCE));
    }

    static <T> int distinctAfterRace(Supplier<T> factory) throws InterruptedException {
        int threads = 200;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threads);
        Set<T> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        var pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                seen.add(factory.get());
                done.countDown();
            });
        }
        start.countDown();
        done.await();
        pool.shutdown();
        return seen.size();
    }
}
