package ru.zheleznikov.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleIteratorTest {

    String [] strings = new String[3];
    SimpleIterator<String> s = new SimpleIterator<>(strings);

    @BeforeEach
    void setup() {
        strings[0] = "string 0";
        strings[1] = "string 1";
        strings[2] = "string 2";
    }


    @Test
    public void iteratorShouldWork() {
//        while (s.hasNext()) {
//            System.out.println(s.next());
//        }
        Assertions.assertTrue(s.hasNext());
        Assertions.assertEquals(s.next(), strings[0]);
        Assertions.assertTrue(s.hasNext());
        Assertions.assertEquals(s.next(), strings[1]);
        Assertions.assertTrue(s.hasNext());
        Assertions.assertEquals(s.next(), strings[2]);
        Assertions.assertFalse(s.hasNext());
    }

}