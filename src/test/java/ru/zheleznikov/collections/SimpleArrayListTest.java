package ru.zheleznikov.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayListTest {

    SimpleArrayList<String> strings = new SimpleArrayList<>();
    String abc = "abc";
    String foo = "foo";
    String bar = "bar";
    String baz = "baz";
    String foobar = "foobar";

    @BeforeEach
    public void setup() {
        strings.add(abc);
        strings.add(foo);
        strings.add(bar);
        strings.add(baz);
    }

    @Test
    public void getShouldWork() {
        boolean add = strings.add(abc);
        Assertions.assertTrue(add);
    }

    @Test
    public void getShouldReturnCorrectElement() {
        Assertions.assertEquals(strings.get(0), abc);
        Assertions.assertEquals(strings.get(1), foo);
        Assertions.assertEquals(strings.get(2), bar);
        Assertions.assertEquals(strings.get(3), baz);
    }

    @Test
    public void elementShouldBeRemoved() {
        strings.delete(0);

        Assertions.assertEquals(strings.get(0), foo);
        Assertions.assertEquals(strings.get(1), bar);
        Assertions.assertEquals(strings.get(2), baz);

        strings.delete(1);
        Assertions.assertEquals(strings.get(0), foo);
        Assertions.assertEquals(strings.get(1), baz);
    }

    @Test
    public void elementShouldBeUpdated() {
        strings.update(2, foobar);
        Assertions.assertEquals(strings.get(0), abc);
        Assertions.assertEquals(strings.get(1), foo);
        Assertions.assertEquals(strings.get(2), foobar);
        Assertions.assertEquals(strings.get(3), baz);
    }

    @Test
    public void clearTest() {
        for (String s : strings) {
            System.out.println(s);
        }
        strings.clear();
        for (String s : strings) {
            System.out.println(s);
        }
    }
}