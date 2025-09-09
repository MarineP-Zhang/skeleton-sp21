package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    public void testInitiate() {
        LinkedListDeque<Integer> test = new LinkedListDeque<Integer>();
        assertEquals(0,test.size());
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<String> test = new ArrayDeque<>();

        test.addFirst("1a");
        test.addFirst("2a");
        test.addFirst("3a");
        test.addFirst("4a");
        test.addFirst("5a");
        test.addFirst("6a");
        test.printDeque();

        test.addLast("7a");
        test.addLast("8a");
        test.printDeque();

        test.addLast("9a");
        test.printDeque();
        test.addFirst("10a");
        test.printDeque();
        assertEquals(10, test.size());
        test.printDeque();
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> arrayTest = new ArrayDeque<>();
        LinkedListDeque<Integer> linkedTest = new LinkedListDeque<Integer>();

        int N = 500;

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniformInt(0,7);
            int randVal = StdRandom.uniformInt(0,100);
            arrayTest.printDeque();
            linkedTest.printDeque();
            if (operationNumber == 0) {
                //addLast
                arrayTest.addLast(randVal);
                linkedTest.addLast(randVal);
            } else if (operationNumber == 1) {
                //addFirst
                arrayTest.addFirst(randVal);
                linkedTest.addFirst(randVal);
            } else if (operationNumber == 2) {
                //removeFirst
                if (arrayTest.isEmpty() || linkedTest.isEmpty()) {
                    continue;
                }
                int arrayFirst = arrayTest.removeFirst();
                int linkedFirst = linkedTest.removeFirst();
                assertEquals(arrayFirst, linkedFirst);
            } else if (operationNumber == 3) {
                //removeLast
                if (arrayTest.isEmpty() || linkedTest.isEmpty()) {
                    continue;
                }
                int arrayLast = arrayTest.removeLast();
                int linkedLast = linkedTest.removeLast();
                assertEquals(arrayLast, linkedLast);
            } else if (operationNumber == 4) {
                assertEquals(arrayTest.size(), linkedTest.size());
            } else if (operationNumber == 5) {
                Iterator<Integer> arrayIterator = arrayTest.iterator();
                Iterator<Integer> linkedIterator = linkedTest.iterator();
                assertTrue(arrayTest.equals(linkedTest));

                for (int j = 0; j < arrayTest.size() - 1; j++) {

                    assertTrue(arrayIterator.hasNext());
                    assertTrue(linkedIterator.hasNext());
                    int arrayNext = arrayIterator.next();
                    int linkedNext = linkedIterator.next();
                    System.out.println("arrayNext: " + arrayNext + " linkedNext: " + linkedNext);
                    assertEquals(arrayNext, linkedNext);
                }
            } else if (operationNumber == 6) {
                assertTrue(arrayTest.equals(linkedTest));
            }
        }
    }
}
