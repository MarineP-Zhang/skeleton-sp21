package deque;

import org.junit.Assert;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedListDeque<T> implements Deque<T>{

    private class LinkedNode<T> {
        public T Item;
        public LinkedNode<T> previous;
        public LinkedNode<T> next;

        public LinkedNode() {
            Item = null;
            previous = null;
            next = null;
        }

        public LinkedNode(T data) {
            Item = data;
            previous = null;
            next = null;
        }

        public LinkedNode(T data, LinkedNode<T> prev, LinkedNode<T> next) {
            Item = data;
            this.previous = prev;
            this.next = next;
        }

    }

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
     * Add item to the head of deque.
     * @param item cannot be null.
     */
    @Override
    public void addFirst(T item){
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
    public void addLast(T item){
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
    public int size(){
        return size;
    }

    /**
     * Print all the Item in deque in order.
     */
    @Override
    public void printDeque(){
        LinkedNode<T> curr = headSentinel.next;
        while (curr != null) {
            System.out.print(curr.Item + " ");
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
    public T removeFirst(){
        if (size == 0) {
            return null;
        } else {
            LinkedNode<T> currentFirst = headSentinel.next;
            headSentinel.next = currentFirst.next;
            if (size == 1) {
                headSentinel.next = null;
                tailSnetinel.previous = null;
            }else {
                headSentinel.next.previous = null;
            }
            size--;
            return currentFirst.Item;
        }
    }

    /**
     * Remove the last element in deque, and return its stored item.
     * assert the size is not zero.
     * @return item in the last node of deque.
     */
    @Override
    public T removeLast(){
        if (size == 0) {
            return null;
        } else {
            LinkedNode<T> currentLast = tailSnetinel.previous;
            tailSnetinel.previous = currentLast.previous;
            if (size == 1) {
                headSentinel.next = null;
                tailSnetinel.previous = null;
            }else {
                tailSnetinel.previous.next = null;
            }
            size--;
            return currentLast.Item;
        }
    }

    /**
     * Get the Nth item in the deque depend on given index
     * @param index the N th node in deque
     * @return Item stored in the node
     */
    @Override
    public T get(int index){
        if (index >= size || index < 0) {
            return null;
        } else {
            LinkedNode<T> curr = headSentinel.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.Item;
        }
    }


    private T helperRecursive (int index, LinkedNode<T> curr) {
        if (index == 0) {
            return curr.Item;
        } else {
            return helperRecursive(index - 1, curr.next);
        }
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        } else if (index == 0) {
            return headSentinel.next.Item;
        } else {
            return helperRecursive(index - 1, headSentinel.next.next);
        }
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        public LinkedNode<T> currentNode;

        public LinkedListDequeIterator() {
            currentNode = headSentinel.next;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public T next() {
            currentNode = currentNode.next;
            return currentNode.Item;
        }
    }

    /**
     * TODO: Finish this.
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (! (o instanceof Deque<?>)) {
            return false;
        } else {
            return false;
        }
    }

}