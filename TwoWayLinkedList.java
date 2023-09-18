import java.util.ListIterator;

public class TwoWayLinkedList<E> extends MyLinkedList<E> {

    // Constructor
    public TwoWayLinkedList() {
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new TwoWayListIterator(index);
    }

    // Inner class for the doubly linked list iterator
    private class TwoWayListIterator implements ListIterator<E> {
        private Node<E> current; // Current index
        private int currentIndex; // Current index position

        public TwoWayListIterator() {
            current = head;
            currentIndex = 0;
        }

        public TwoWayListIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            current = head;
            currentIndex = 0;

            while (currentIndex < index) {
                current = current.next;
                currentIndex++;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E e = current.element;
            current = current.next;
            currentIndex++;
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new java.util.NoSuchElementException();
            }
            if (current == null) {
                current = tail;
            } else {
                current = current.previous;
            }
            currentIndex--;
            return current.element;
        }

        @Override
        public int nextIndex() {
            return currentIndex;
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
            if (current == null) {
                throw new IllegalStateException();
            }
            current.element = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("add() is not supported");
        }
    }
}
