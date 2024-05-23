package org.bexterlab.demo;

import static java.util.Objects.isNull;

public class FunctionalInterfaceDemo {

    public static void main(String... args) {
        var functionalInterfaceDemo = new FunctionalInterfaceDemo();
        functionalInterfaceDemo.executeBusinessLogic(functionalInterfaceDemo::toUpperCase,"Hello World");
        functionalInterfaceDemo.executeBusinessLogic(functionalInterfaceDemo::toUpperCase, null);
    }

    private void executeBusinessLogic(BusinessLogic<String, String> businessLogic,String from) {
        try {
            System.out.println(businessLogic.execute(from));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private String toUpperCase(String from) {
        if (isNull(from)) {
            throw new RuntimeException("Input is null");
        }
        return from.toUpperCase();
    }

    @FunctionalInterface
    private interface BusinessLogic<F, T> {
        T execute(F from);
    }
}
