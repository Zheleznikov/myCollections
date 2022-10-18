package ru.zheleznikov.collections;

import java.util.Iterator;

/**
 * Реализация простого контейнера для хранения элементов по типу LinkedList.
 * В основе лежит объект, который хранит текущий элемент и ссылку на предыдущий и следующий.
 * Есть два дефолтных объекта: первый и последний, которые позволяют реализовать логику добавления элементов в начало и конец.
 * Позволяет добавить элемент в конец.
 * Позволяет добавить элемент в начало
 * Позволяет получить элемент по индексу
 * <p>
 * Контейнер реализует два простых итератора: прямой и реверсивный
 */
public class SimpleLinkedList<E> implements SimpleLinked<E>, DescendingIterator<E> {

    private Node<E> firstNodeMock;
    private Node<E> lastNodeMock;
    private int size = 0;

    public SimpleLinkedList() {
        lastNodeMock = new Node<>(null, firstNodeMock, null);
        firstNodeMock = new Node<>(null, null, lastNodeMock);
    }

    /**
     * Создается нода и есть присваивается ссылка на последнюю ноду. Далее вновь созданной ноде задается новое значение.
     * После этого для последней ноды создается новый объект. В нем хранится ссылка на вновь добавленный элемент.
     * Добавленному элементу присваивается ссылка на пересозданную последнюю ноду
     */
    @Override
    public void addLast(E e) {
        Node<E> last = lastNodeMock;
        last.setCurrentElement(e);
        lastNodeMock = new Node<>(null, last, null);
        last.setNextNode(lastNodeMock);
        size++;
    }

    @Override
    public void addFirst(E e) {
        Node<E> first = firstNodeMock;
        first.setCurrentElement(e);
        firstNodeMock = new Node<>(null, null, first);
        first.setPrevNode(firstNodeMock);
        size++;
    }

    /**
     * Создается целевая нода, ей присваивается ссылка на первую настоящую ноду из списка.
     * Далее с помощью цикла у каждой следующей ноды вызывается метод получения следующей ноды, пока не дойдем до нужной по индексу.
     */
    @Override
    public E getElementByIndex(int index) {
        Node<E> target = firstNodeMock.getNextNode();
        for (int i = 0; i < index; i++) {
            target = getNextNodeSupport(target);
        }
        return target.getCurrentElement();
    }

    private Node<E> getNextNodeSupport(Node<E> current) {
        return current.getNextNode();
    }



    /**
     * Находим ноду до удаляемого элемента и ноду после удаляемого элемента
     * Присваиваем им ссылки друг на друга - таким образом исключаем удаляемую ноду из списка
     *
     * @param index
     */
    public void deleteByIndex(int index) { // Баг. не удаляет нулевой элемент
        Node<E> before = firstNodeMock.getNextNode();
        for (int i = 0; i < index - 1; i++) {
            before = getNextNodeSupport(before);
        }

        Node<E> after = firstNodeMock.getNextNode();
        for (int i = 0; i < index + 1; i++) {
            after = getNextNodeSupport(after);
        }

        before.setNextNode(after);
        after.setPrevNode(before);
    }

    public void deleteFirst() {
        Node<E> first = firstNodeMock.getNextNode(); // получили первую ноду, на которую хотим убрать ссылку
        first = getNextNodeSupport(first);
        firstNodeMock.setNextNode(first);
        first.setPrevNode(firstNodeMock);
        size--;
    }

    public void deleteLast() { // Баг - не находит элементы, добавленные методом addFirst
        Node<E> last = lastNodeMock.getPrevNode(); // взяли настоящую запись последюю и передали на нее ссылку в last
        last = last.getPrevNode(); // в last присвоили ссылку на предпоследнюю ноду
        lastNodeMock.setPrevNode(last); // в мок-ноду присвоили ссылку на предпоследний элемент
        last.setNextNode(lastNodeMock); // в предпоследнюю ноду присвоили ссылку на мок-ноду

        size--;
    }

    private Node<E> getPrevNodeSupport(Node<E> current) {
        return current.getPrevNode();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Object next() {
                return getElementByIndex(counter++);
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {

            private int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElementByIndex(counter--);
            }
        };
    }

    private class Node<E> {
        private E currentElement;
        private Node<E> nextNode;
        private Node<E> prevNode;


        private Node(E currentElement, Node<E> prevNode, Node<E> nextNode) {
            this.currentElement = currentElement;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public void setPrevNode(Node<E> prevNode) {
            this.prevNode = prevNode;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public Node<E> getPrevNode() {
            return prevNode;
        }
    }
}
