package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesDouble() {
        IntList lst = IntList.of(14, 15, 17, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 289 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesTriple() {
        IntList lst = IntList.of(14, 7, 17, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 49 -> 289 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesLastOne() {
        IntList lst = IntList.of(14, 7, 17, 17, 5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 49 -> 289 -> 289 -> 25", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesNoPrime() {
        IntList lst = IntList.of(4,4,4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 4 -> 4", lst.toString());
        assertTrue(!changed);
    }
}
