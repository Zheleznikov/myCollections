package ru.zheleznikov.test;

import ru.zheleznikov.annotations.AfterZhele;
import ru.zheleznikov.annotations.BeforeZhele;
import ru.zheleznikov.annotations.TestZhele;
import ru.zheleznikov.collections.SimpleArrayList;

import static ru.zheleznikov.test.AssertZhele.assertItems;

public class SimpleArrayZheleTest {


    SimpleArrayList<String> strings = new SimpleArrayList<>();
    String abc = "abc";
    String foo = "foo";
    String bar = "bar";
    String baz = "baz";
    String foobar = "foobar";

    @BeforeZhele
    public void setup() {
        System.out.println("SETUP");
        strings.add(abc);
        strings.add(foo);
        strings.add(bar);
        strings.add(baz);
    }

    @AfterZhele
    public void tearDown() {
        strings.clear();
        System.out.println("TEAR_DOWN\n");
    }

    @TestZhele
    public void addShouldWork() {
        System.out.println("TEST - addShouldWork");
        boolean add = strings.add(abc);
        assertItems(abc, strings.get(4));

    }

    @TestZhele
    public void getShouldReturnCorrectElement() {
        System.out.println("TEST - getShouldReturnCorrectElement");
        assertItems(abc, strings.get(0));
        assertItems(foo, strings.get(1));
        assertItems(bar, strings.get(2));
        assertItems(baz, strings.get(3));
    }

    @TestZhele
    public void elementShouldBeRemoved() {
        System.out.println("TEST - elementShouldBeRemoved");

        strings.delete(0);

        assertItems(abc, strings.get(0));
        assertItems(foo, strings.get(1));
        assertItems(bar, strings.get(2));

        strings.delete(1);
        assertItems(abc, strings.get(0));
        assertItems(foo, strings.get(1));
        assertItems(foobar, strings.get(2)); // ошибка
    }

    @TestZhele
    public void elementShouldBeUpdated() {
        System.out.println("TEST - elementShouldBeUpdated");

        strings.update(2, foobar);

        assertItems(abc, strings.get(0));
        assertItems(foo, strings.get(1));
        assertItems(foobar, strings.get(2));
        assertItems(baz, strings.get(3));
    }

    @TestZhele
    public void clearArrayTest() {
        System.out.println("TEST - clearArrayTest");

        assertItems(4, strings.size());
        strings.clear();
        assertItems(1, strings.size()); // ошибка. Должно быть 0

    }


}
