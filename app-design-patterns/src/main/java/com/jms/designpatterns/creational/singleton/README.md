# Singleton Design Pattern

### Intent of Singleton Design Pattern

Ensure a class only has one instance, and provide a global point of access to this singleton instance.

### Real-World Applications of Singleton Pattern

* The logging class
* Configuration classes in many applications
* Connection pools
* File manager
* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)

### Types of Singleton Implementations in Java

| | Eagerly Initialized | Thread-Safe Lazy Initialized | Thread-Safe Double Checked Locking |
|--|---|---|---|
| Instance Creation | At class loading | On first call to `getInstance()` | On first call to `getInstance()` |
| Thread Safety | Guaranteed | Guaranteed through synchronization | Theoretically guaranteed, but issues in Java < 1.5 |
| Performance | Best | Worst (due to synchronization) | Potentially better than synchronized, but issues in Java < 1.5 |
| Memory Usage | Higher (instance created even if not used) | Lower (instance created only when needed) | Lower (instance created only when needed) |
| Complexity | Simplest | More complex due to synchronization | Most complex due to synchronization and potential issues |
| Recommendation | Generally preferred | Use when lazy initialization is crucial and performance is not critical | Avoid due to potential issues and complexity |

### References and Credits

https://github.com/iluwatar/java-design-patterns