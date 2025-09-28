package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> bugless = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            bugless.addLast(i + 4);
            bug.addLast(i + 4);
        }
        assertEquals(bug.size(), bugless.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(bug.removeLast(), bugless.removeLast());
        }
    }
    @Test
    public void randomizedTes() {
        AListNoResizing<Integer> bugless_L = new AListNoResizing<>();
        BuggyAList<Integer> bug_L = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0 || operationNumber == 3) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                bugless_L.addLast(randVal);
                bug_L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int bugless_size = bugless_L.size(), bug_size = bug_L.size();
                System.out.println("size(" + bugless_size + bug_size + ")");
                assertEquals(bug_size, bugless_size);
            } else {
                //removeLast
                if (bug_L.size() == 0 || bugless_L.size() == 0) {
                    continue;
                }
                int bugless_out = bugless_L.removeLast();
                int bug_out = bug_L.removeLast();
                System.out.println("removeLast(" + bugless_out + bug_out + ")");
                assertEquals(bugless_out, bug_out);
            }
        }
    }
    @Test
    public void testBoundaryResize() {
        AListNoResizing<Integer> bugless = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();
        for (int i = 0; i < 999999; i++) {
            bug.addLast(9);
        }
    }
}