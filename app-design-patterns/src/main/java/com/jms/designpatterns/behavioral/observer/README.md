# Observer Design Pattern

### Intent of Observer Design Pattern

The Observer Design Pattern establishes a one-to-many dependency between objects. When an object (subject) changes its state, all its dependent objects (observers) are notified and updated automatically. This allows for loose coupling and efficient communication between objects.

### Example of Observer Design Pattern in Action

This example illustrates  how the Observer pattern can be used to implement a group notification system.

#### Components

- **Subject**: `Group` class maintains a list of registered observers and provides methods to add, remove, and notify them.
- **Observer**: `GroupObserver` interface defines the `notify(String message)` method that observers implement to receive notifications. The User class implements this interface and logs received messages.

#### Key Points

- The `Group` class maintains a central list of registered observers.
- Observers implement the `GroupObserver` interface, defining the callback method for receiving notifications.
- The `Group` class provides methods for adding and removing observers from its list.
- When the `Group` needs to send a notification, it iterates through its observer set (maintained in insertion order due to the use of LinkedHashSet) and calls the `notify` method on each observer, passing the message.
- This approach allows for loose coupling as observers only need to implement the `GroupObserver` interface and are unaware of the specific `Group` instance sending notifications.