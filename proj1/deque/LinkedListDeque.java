package deque;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private LinkedNode<T> headSentinel;
    private LinkedNode<T> tailSnetinel;
    private int size;

    /**
     * Constructor: set size to zero.
     */
    public LinkedListDeque() {
        this.headSentinel = new LinkedNode<T>();
        this.tailSnetinel = new LinkedNode<T>();
        this.size = 0;
    }

    /**
     * @param o compared object
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (!(o instanceof Deque<?>)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Add item to the head of deque.
     * @param item cannot be null.
     */
    @Override
    public void addFirst(T item) {
        assertNotNull(item);
        LinkedNode<T> newNode = new LinkedNode<T>(item);

        if (size == 0) {
            headSentinel.next = newNode;
            tailSnetinel.previous = newNode;
        } else {
            LinkedNode<T> currentFirst = headSentinel.next;
            currentFirst.previous = newNode;
            newNode.next = currentFirst;
            headSentinel.next = newNode;
        }

        size++;
    }

    /**
     * Add item to tail of the deque.
     * @param item cannot be null.
     */
    @Override
    public void addLast(T item) {
        assertNotNull(item);
        LinkedNode<T> newNode = new LinkedNode<T>(item);

        if (size == 0) {
            headSentinel.next = newNode;
        } else {
            LinkedNode<T> currentLast = tailSnetinel.previous;
            currentLast.next = newNode;
            newNode.previous = currentLast;
        }
        tailSnetinel.previous = newNode;

        size++;
    }

    /**
     *
     * @return the size of deque
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Print all the Item in deque in order.
     */
    @Override
    public void printDeque() {
        LinkedNode<T> curr = headSentinel.next;
        while (curr != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * Remove the first element in deque, and return its stored item.
     * Assert size is not zero.
     * @return item in the head of deque.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            LinkedNode<T> currentFirst = headSentinel.next;
            headSentinel.next = currentFirst.next;
            if (size == 1) {
                headSentinel.next = null;
                tailSnetinel.previous = null;
            } else {
                headSentinel.next.previous = null;
            }
            size--;
            return currentFirst.item;
        }
    }

    /**
     * Remove the last element in deque, and return its stored item.
     * assert the size is not zero.
     * @return item in the last node of deque.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            LinkedNode<T> currentLast = tailSnetinel.previous;
            tailSnetinel.previous = currentLast.previous;
            if (size == 1) {
                headSentinel.next = null;
                tailSnetinel.previous = null;
            } else {
                tailSnetinel.previous.next = null;
            }
            size--;
            return currentLast.item;
        }
    }

    /**
     * Get the Nth item in the deque depend on given index
     * @param index the N th node in deque
     * @return Item stored in the node
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            LinkedNode<T> curr = headSentinel.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.item;
        }
    }


    /**
     * uhrufru
     * @param index furhi
     * @param curr friuhfeiu
     * @return nfuieri
     */
    private T helperRecursive(int index, LinkedNode<T> curr) {
        if (index == 0) {
            return curr.item;
        } else {
            return helperRecursive(index - 1, curr.next);
        }
    }

    /**
     * hfrhei
     * @param index  fre
     * @return fre
     */
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        } else if (index == 0) {
            return headSentinel.next.item;
        } else {
            return helperRecursive(index - 1, headSentinel.next.next);
        }
    }

    /**
     * fr
     * @return fref
     */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private LinkedNode<T> currentNode;

        /**
         * fefeuyg
         */
        public LinkedListDequeIterator() {
            currentNode = headSentinel.next;
        }

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            return currentNode.next != null;
        }

        @Override
        public T next() {
            if (size == 0) {
                return null;
            }
            currentNode = currentNode.next;
            return currentNode.item;
        }
    }


    private class LinkedNode<T> {
        private T item;
        private LinkedNode<T> previous;
        private LinkedNode<T> next;

        /**
         * basic data structure for building linked list
         */
        public LinkedNode() {
            item = null;
            previous = null;
            next = null;
        }

        /**
         * Constructor with given data
         * @param data data you want stored as first element
         */
        public LinkedNode(T data) {
            item = data;
            previous = null;
            next = null;
        }

        /**
         * Constructor with all parameters
         * @param data node's data
         * @param prev previous node
         * @param next next node
         */
        public LinkedNode(T data, LinkedNode<T> prev, LinkedNode<T> next) {
            item = data;
            this.previous = prev;
            this.next = next;
        }

    }

}