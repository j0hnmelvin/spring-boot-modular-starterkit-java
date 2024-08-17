package com.jms.designpatterns.creational.singleton;

/**
 * Thread-safe Singleton class. The instance is lazily initialized and thus needs synchronization
 * mechanism.
 */
public final class ThreadSafeLazyInitialized {

    /**
     * Singleton instance of the class, declared as volatile to ensure atomic access by multiple threads.
     */
    private static volatile ThreadSafeLazyInitialized instance;

    /**
     * private constructor to prevent client from instantiating.
     */
    private ThreadSafeLazyInitialized() {
        // Protect against instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("ThreadSafeLazyInitialized already initialized.");
        }
        System.out.println("An instance of ThreadSafeLazyInitialized created.");
    }

    /**
     * The instance doesn't get created until the method is called for the first time.
     * The code performs synchronization every time getInstance() is called.
     * The "double-checked locking" idiom tries to avoid synchronization after the instance is allocated.
     * @return an instance of the class.
     */
    public static synchronized ThreadSafeLazyInitialized getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLazyInitialized();
        }
        return instance;
    }
}
