import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

interface MyList<E> {
    void add(E element);
    int size();
    boolean isEmpty();
    E get(int index);
    boolean contains(E element);
    Iterator<E> iterator();
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);
}

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E element) {
            data = element;
            next = null;
        }
    }

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return new LinkedListIterator(index);
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current;
        private int currentIndex;

        LinkedListIterator() {
            current = head;
            currentIndex = -1;
        }

        LinkedListIterator(int index) {
            current = head;
            currentIndex = -1;

            while (currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data;
            current = current.next;
            currentIndex++;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = head;
                currentIndex = size - 1;
                while (currentIndex > 0) {
                    current = current.next;
                    currentIndex--;
                }
                return current.data;
            } else {
                Node<E> previous = head;
                for (int i = 0; i < currentIndex - 1; i++) {
                    previous = previous.next;
                }
                currentIndex--;
                current = previous;
                return previous.data;
            }
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("set() is not supported");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("add() is not supported");
        }
    }

    // ... (rest of the MyLinkedList class remains the same)
}
