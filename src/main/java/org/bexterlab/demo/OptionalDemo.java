package org.bexterlab.demo;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String... args) {
        OptionalDemo demo = new OptionalDemo();
        System.out.println(demo.toUpperCase(Optional.of("hello")));
        System.out.println(demo.toUpperCase(Optional.empty()));
    }

    private String toUpperCase(Optional<String> value) {
        return value.map(String::toUpperCase).orElse(null);
    }
}
