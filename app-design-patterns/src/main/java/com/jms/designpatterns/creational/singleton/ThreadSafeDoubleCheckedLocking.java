package com.jms.designpatterns.creational.singleton;

/**
 * Double check locking.
 *
 * http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 *
 * Broken in Java < 1.5.
 */
public final class ThreadSafeDoubleCheckedLocking {

    /**
     * Singleton instance of the class, declared as volatile to ensure atomic access by multiple threads.
     */
    private static volatile ThreadSafeDoubleCheckedLocking instance;

    /**
     * private constructor to prevent client from instantiating.
     */
    private ThreadSafeDoubleCheckedLocking() {
        // to prevent instantiating by Reflection call
        if (instance != null) {
            throw new IllegalStateException("ThreadSafeDoubleCheckedLocking already initialized.");
        }
        System.out.println("An instance of ThreadSafeDoubleCheckedLocking created.");
    }

    /**
     * The instance doesn't get created until the method is called for the first time.
     *
     * @return an instance of the class.
     */
    public static ThreadSafeDoubleCheckedLocking getInstance() {
        // local variable increases performance by 25 percent
        // Joshua Bloch "Effective Java, Second Edition", p. 283-284

        var result = instance;
        // Check if singleton instance is initialized.
        // If it is initialized then we can return the instance.
        if (result == null) {
            // It is not initialized, but we cannot be sure because some other thread might have
            // initialized it in the meanwhile.
            // So to make sure we need to lock on an object to get mutual exclusion.
            synchronized (ThreadSafeDoubleCheckedLocking.class) {
                // Again assign the instance to local variable to check if it was initialized by some
                // other thread while current thread was blocked to enter the locked zone.
                // If it was initialized then we can return the previously created instance
                // just like the previous null check.
                result = instance;
                if (result == null) {
                    // The instance is still not initialized, so we can safely
                    // (no other thread can enter this zone)
                    // create an instance and make it our singleton instance.
                    result = new ThreadSafeDoubleCheckedLocking();
                    instance = result;
                }
            }
        }
        return result;
    }
}
