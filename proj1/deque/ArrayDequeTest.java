package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public void test1() {

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

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

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeLast();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }
    @Test
    public void equalsTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.removeLast();
        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);
        lld1.addFirst(40);

        lld2.addLast(40);
        lld2.addLast(30);
        lld2.addLast(20);
        lld2.addLast(10);
        if(lld1.equals(lld2)){
            System.out.print("yes");
        }else {
            System.out.print("no");
        }
    }

    @Test
    public void iteratorTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for(int i = 0;i < 50;i++) {
            lld1.addLast(i);
        }
        System.out.print(lld1.removeFirst());
        for (int i = 0;i < 45;i++) {
            System.out.print(lld1.removeLast());
        }

        MaxArrayDeque<Integer> MAD = new MaxArrayDeque<Integer>(null);
        MAD.addFirst(1);
    }

}
