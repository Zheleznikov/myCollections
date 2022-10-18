package ru.zheleznikov.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTest {

    SimpleLinkedList<String> strings = new SimpleLinkedList<>();
    String abc = "1";
    String foo = "2";
    String bar = "3";
    String baz = "4";

    @BeforeEach
    public void setup() {
        strings.addFirst(abc);
        strings.addFirst(foo);
        strings.addFirst(bar);
        strings.addFirst(baz);
    }

    @Test
    public void shouldUseForEach() {
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void descendingIteratorShouldWork() {
        Iterator iterator = strings.descendingIterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(iterator.next(), abc);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(iterator.next(), foo);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(iterator.next(), bar);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(iterator.next(), baz);
        Assertions.assertFalse(iterator.hasNext());

    }

    @Test
    public void elementShouldBeGotByIndex() {
        String actual = strings.getElementByIndex(2);
        Assertions.assertEquals(foo, actual);
    }

    @Test
    public void elementShouldBeDeletedByIndex() {
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("----");
        System.out.println("0 element: " + strings.getElementByIndex(0));
        strings.deleteByIndex(0);
        System.out.println("----");
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void firstElementShouldBeDeleted() {
        for (String s : strings) {
            System.out.println(s);
        }
        System.out.println("0 element: " + strings.getElementByIndex(0));
        System.out.println("----");
        strings.deleteFirst();
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void lastElementShouldBeDeleted() {
        SimpleLinkedList<String> strings2 = new SimpleLinkedList<>();
        strings2.addLast("first");
        strings2.addLast("last");

        for (String s : strings2) {
            System.out.println(s);
        }
        System.out.println("0 element: " + strings2.getElementByIndex(0));
        System.out.println("----");
//        strings.deleteFirst();
        strings2.deleteLast();
        for (String s : strings2) {
            System.out.println(s);
        }
    }

    @Test
    public void comboAdFirstAndAddLast() {
        SimpleLinkedList<String> strings2 = new SimpleLinkedList<>();
        strings2.addLast("last");
        strings2.addLast("last-last");
        strings2.deleteFirst();

        for (String s : strings2) {
            System.out.println(s);
        }
    }
}