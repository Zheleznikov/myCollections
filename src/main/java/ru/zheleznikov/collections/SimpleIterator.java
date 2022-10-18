package ru.zheleznikov.collections;

import java.util.Iterator;

/**
 *
 */
public class SimpleIterator <T> implements Iterator {

    private final T [] objects;
    private int index = 0;

    SimpleIterator(T[] objects) {
        this.objects = objects;
    }

    @Override
    public T next() {
        return objects[index++];
    }

    @Override
    public boolean hasNext() {
        return index < objects.length;
    }

}
