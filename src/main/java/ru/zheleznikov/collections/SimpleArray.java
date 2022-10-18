package ru.zheleznikov.collections;


public interface SimpleArray<E> extends Iterable<E> {
    boolean add(E e);
    E get(int index);
    int size();
    void delete(int index);
    void update(int index, E element);
    void clear();
}
