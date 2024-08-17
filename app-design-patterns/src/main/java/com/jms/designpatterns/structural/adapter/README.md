# Adapter Design Pattern

### Intent of Adapter Design Pattern

The Adapter Design Pattern in Java allows you to convert the interface of a class to another interface that clients expect. This enables compatibility between classes with different interfaces, promoting flexibility and reusability in your code.

### Example of Adapter Design Pattern in Action

This example illustrates how the Adapter Design pattern can be used to adapt a class with an incompatible interface to work with another class.

#### Components

- **Interface**: `JSONDataAnalyticsTool` defines a contract for data analysis, specifying the `analyzeData()` method.
- **Adaptee**: `XMLData` represents the incompatible data format (XML).
- **Adapter**: `XMLAdapter` bridges the gap between `XMLData` and the `JSONDataAnalyticsTool` interface.
  - It implements `JSONDataAnalyticsTool` and provides its own implementation for `analyzeData()`.
  - Internally, it converts XML to JSON and delegates the analysis to a `JSONDataAnalyticsToolImpl` instance using the converted data.
- **Client**: `Client` demonstrates the flexibility of the Adapter pattern. It treats both `JSONDataAnalyticsToolImpl` (directly providing JSON data) and `XMLAdapter` (adapting XML data) in the same way through `processData()`.

#### Key Points

- The `JSONDataAnalyticsTool` interface defines the core functionality for data analysis.
- The `XMLAdapter` class acts as the adapter, implementing the `JSONDataAnalyticsTool` interface and adapting `XMLData` to the expected format.
- The `Client` class interacts with both data formats seamlessly through the common interface, highlighting the pattern's benefit.