package ru.zheleznikov.test;

public class AssertZhele {

    public static void assertItems(String expected, String actual) {
        if (!expected.equals(actual)) throw new AssertionError("Значения не равны");
    }

    public static void assertItems(int expected, int actual) {
        if (expected != actual) throw new AssertionError("Значения не равны");
    }
}
