package org.bexterlab.demo;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String... args) {
        StreamDemo demo = new StreamDemo();
        System.out.println(demo.toUpperCase("hello"));
        System.out.println(demo.toUpperCase("hello", "world"));
        System.out.println(demo.toUpperCase("hello", null, "world"));
    }

    private String toUpperCase(String... value) {
        return Arrays.stream(value)
                .peek(System.out::println)
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));
    }

}
