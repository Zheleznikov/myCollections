package ru.zheleznikov.collections;

import java.util.Iterator;

/**
 * Реализация простого контейнера для хранения элементов по типу ArrayList. В основе лежит обычный массив и дженерики.
 * Позволяет добавить элемент (элемент добавляется в конец). Надо сказать, что размер массива, которые лежит в основе контейнера
 * увеличивается на 1 каждый раз, когда добавляется элемент.
 * Позволят удалить элемент по индексу. Размер списка уменьшается на 1 каждый раз после удаления элемента.
 * Позволяет получить элемент по индексу.
 * Позволяет обновить элемент по индексу.
 *
 * Контейнер реализует самодельный простой simpleIterator
 */
public class SimpleArrayList<E> implements SimpleArray<E> {

    private E[] values;

    public SimpleArrayList() {
        values = (E[]) new Object[0];
    }

    /**
     * Т.к. в методе используется cast, поэтому вся конструкция обернуто в try-catch.
     * При добавлении элемента создается новый массив, размером на 1 больше предыдущего.
     * Далее с помощью цикла for в новый массив копируются значения из предыдущего. После этого в последнюю свободную ячейку вставляется
     * новое значение.
     */
    @Override
    public boolean add(E t) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                values[i] = temp[i];
            }
            values[values.length - 1] = t;
            return true;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clear() {
        values =(E[]) new Object[0];
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    /**
     * Т.к. в методе используется cast, поэтому вся конструкция обернуто в try-catch.
     * Создается новый массив на основе текущего. Для текущего создается новый, размером меньше на 1 элемент.
     * После этого в цикле проходим до удаляемого элемента и добавляем элементы в исходный массив.
     * Далее в цикле проходим начиная со следующего элемента после удаленного и добавляем элементы в исходный массив.
     */
    @Override
    public void delete(int index) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            for (int i = 0; i < index; i++) {
                values[i] = temp[i];
            }

            for (int i = index + 1; i < temp.length; i++) {
                values[i - 1] = temp[i];
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int index, E element) {
        values[index] = element;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Iterator iterator() {
        return new SimpleIterator<>(values);
    }
}
