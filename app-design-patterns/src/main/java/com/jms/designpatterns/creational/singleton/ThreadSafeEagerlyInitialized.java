package com.jms.designpatterns.creational.singleton;

/**
 * Singleton class. Eagerly initialized static instance guarantees thread safety.
 */
public final class ThreadSafeEagerlyInitialized {

    /**
     * Singleton instance of the class.
     */
    private static final ThreadSafeEagerlyInitialized INSTANCE = new ThreadSafeEagerlyInitialized();

    /**
     * private constructor to prevent client from instantiating.
     */
    private ThreadSafeEagerlyInitialized(){
        System.out.println("An instance of ThreadSafeEagerlyInitialized created.");
    }

    /**
     * To be called by client to obtain instance of the class.
     *
     * @return instance of the singleton.
     */
    public static ThreadSafeEagerlyInitialized getInstance() {
        return INSTANCE;
    }
}
