package ru.zheleznikov.collections;

public interface SimpleLinked<E> extends Iterable<E> {
    void addLast(E e);
    void addFirst(E e);
    E getElementByIndex(int index);
    void deleteByIndex(int index);
}
