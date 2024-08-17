# Factory Design Pattern

### Intent of Factory Design Pattern

The Factory Design Pattern offers a way to create objects without directly exposing the creation logic to the client code. It provides a central place to handle object creation based on specific criteria, promoting loose coupling and flexibility.

### Example of Factory Design Pattern in Action

This example illustrates how the Factory pattern can be used to create different types of Cake objects based on a provided CakeType enum.

#### Components

- **Product Interface**: `Cake` defines a contract for cake objects, specifying a `getDescription()` method to retrieve the cake's description.
- **Concrete Products**: `ChocolateCake`, `StrawberryCake` and `VanillaCake` represent specific types of cakes that implement the `Cake` interface and provide their own descriptions.
- **Creator**: `CakeFactory` acts as the factory, responsible for creating and returning `Cake` objects based on the provided `CakeType`. It uses conditional logic to determine the appropriate concrete product to create.
- **Client**: The main code utilizes the `CakeFactory` to obtain the desired `Cake` objects and then calls their `getDescription()` method.

#### Key Points

- The `Cake` interface defines a common functionality for all types of cakes.
- The `CakeFactory` class centralizes the logic for creating different cake objects based on the `CakeType` enum.
- The client code interacts primarily with the `CakeFactory` to obtain `Cake` instances. This promotes loose coupling, as the client is unaware of the specific cake types being created.
- This approach allows for future extensibility by adding new cake types without modifying the client code.