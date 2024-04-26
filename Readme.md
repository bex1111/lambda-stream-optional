# Lambda Expressions and Functional Interfaces

```java
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

Converter<String, Integer> stringToInteger = Integer::parseInt;
Integer number = stringToInteger.convert("123"); // Outputs: 123

Converter<Integer, String> integerToString = (from) -> String.valueOf(from);
String result = integerToString.convert(456); // Outputs: "456"
```

```java
// Runnable
Runnable runnable = () -> System.out.println("This is a Runnable using a lambda expression.");

// Supplier
Supplier<Double> randomSupplier = () -> Math.random();

// Consumer
Consumer<String> printConsumer = s -> System.out.println("Consumer prints: " + s);

// Function
Function<String, Integer> stringToLength = s -> s.length();

// Predicate
Predicate<Integer> isEven = n -> n % 2 == 0;

// BiConsumer
BiConsumer<String, Integer> repeatPrint = (s, n) -> {
    for (int i = 0; i < n; i++) {
        System.out.println(s);
    }
};

// BiFunction
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// UnaryOperator
UnaryOperator<Integer> square = x -> x * x;

// BinaryOperator
BinaryOperator<Integer> multiply = (a, b) -> a * b;
```

# Optional

- `Optional.empty()`
- `Optional.of(value)`
- `Optional.ofNullable(value)`
---
- `isPresent()`
- `isEmpty()`
---
- `get()`
- `orElse(defaultValue)`
- `orElseGet(supplier)`
- `orElseThrow(exceptionSupplier)`
---
- `ifPresent(consumer)`
- `ifPresentOrElse(consumer, emptyAction)`
---
- `map(function)`
- `flatMap(function)`
- `filter(predicate)`

# Java Streams

- `Steam.of(values)`
- `Arrays.stream(array)`
- `Stream.empty()`
- `List.of(values).stream()`
- `IntStream.range(start, end)`
---
- `filter(Predicate)`
- `map(Function)`
- `flatMap(Function)`
- `sorted()`
- `distinct()`
- `limit(long)`
- `skip(long)`
---
- `peek(Consumer)`
---
- `forEach(Consumer)`
---
- `collect(Collector)`
- `reduce(BinaryOperator)`
- `sum()`
- `min()`
- `max()`
- `count()`
- `anyMatch(Predicate)` 
- `allMatch(Predicate)` 
- `noneMatch(Predicate)` 
---
- `findFirst()` 
- `findAny()`

# Useful articles:
- [Java Streams API](https://www.baeldung.com/java-8-streams)
- [Java Optional API](https://www.baeldung.com/java-optional)
- [Java Lambda Expressions](https://www.baeldung.com/java-8-lambda-expressions-tips)