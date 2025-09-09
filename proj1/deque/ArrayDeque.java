package deque;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private static int initialLength = 8;

    private T[] backingArray;
    private int size;
    private int first;

    /**
     * Constructor: build a new deque by initiating a backing array.
     */
    public ArrayDeque() {
        backingArray = (T[]) new Object[initialLength];
        size = 0;
        first = 0;
    }

    /**
     * @param o compared object
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Deque<?>) || this.size() != ((Deque<?>) o).size()) {
            return false;
        } else {
            boolean result = true;
            for (int i = 0; i < this.size(); i++) {
                result &= (this.get(i).equals(((Deque<?>) o).get(i)));
            }
            return result;
        }
    }

    /**
     * Resize the backing array providing enough space or reducing the redundant space
     * @param length new array's length you want
     */
    private void resize(int length) {
        T[] tmpArray = (T[]) new Object[length];
        for (int i = 0; i < size; i++) {
            tmpArray[i] = backingArray[(first + i) % backingArray.length];
        }
        backingArray = tmpArray;
    }

    @Override
    public void addFirst(T item) {
        assertNotNull(item);

        if (size == backingArray.length) {
            resize(backingArray.length * 2);
        }

        first = (first - 1 + backingArray.length) % backingArray.length;
        backingArray[first] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        assertNotNull(item);

        if (size == backingArray.length) {
            resize(backingArray.length * 2);
        }
        backingArray[(first + size) % backingArray.length] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = first; i < first + size; i++) {
            System.out.print(backingArray[i % backingArray.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T firstOne = backingArray[first];
        backingArray[first] = null;
        first = (first + 1) % backingArray.length;
        size--;

        if (size < backingArray.length / 4 && backingArray.length >= initialLength * 2) {
            resize(backingArray.length / 2);
        }
        return firstOne;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int last = (first + size) % backingArray.length;
        T lastOne = backingArray[last];
        backingArray[last] = null;
        size--;

        if (size < backingArray.length / 4 && backingArray.length >= initialLength * 2) {
            resize(backingArray.length / 2);
        }
        return lastOne;
    }

    @SuppressWarnings("checkstyle:ParenPad")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return backingArray[ (first + index) % backingArray.length];
    }


    /**
     *
     * @return a iterator of AD
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /**
     * iterator construct class
     */
    private class ArrayDequeIterator implements Iterator<T> {
        private int distance;

        /**
         * Constructor
         */
        public ArrayDequeIterator() {
            distance = 0;
        }

        @Override
        /**
         * tell if has next value in deque
         */
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            return distance <= size;
        }

        /**
         * give next item in the deque
         * @return the next item
         */
        @Override
        public T next() {
            T nextT = backingArray[(first + distance) % backingArray.length];
            distance++;
            return nextT;
        }
    }
}