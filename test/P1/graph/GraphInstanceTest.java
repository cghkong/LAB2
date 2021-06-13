/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for instance methods of Graph.
 *
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    // Testing strategy
    // add() : test the existing / in-existing vertices
    // remove() : test to remove existing / in-existing vertices
    // set() : test negative weight / in-existing vertices should be wrong, and test the updated weight
    // vertices() : check the return vertices with the reality
    // source() target() : empty_map , by the way check the set()
    // Execute a complete test

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // TODO other tests for instance methods of Graph

    @Test
    public void testAdd() {
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        assertEquals(false, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("c"));
        assertEquals(true, emptyInstance.add("d"));
        assertEquals(false, emptyInstance.add("b"));
        assertEquals(true, emptyInstance.add("e"));
        assertEquals(false, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("same"));
        assertEquals(false, emptyInstance.add("same"));
    }

    @Test
    public void testRemove() {
        Graph<String> emptyInstance = emptyInstance();

        // add
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));

        // remove
        assertEquals(true, emptyInstance.remove("b"));
        assertEquals(false, emptyInstance.remove("b"));

        assertEquals(false, emptyInstance.remove("c"));
        assertEquals(true, emptyInstance.add("c"));
        assertEquals(true, emptyInstance.remove("c"));
    }

    @Test
    public void testSet() {
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        assertEquals(true, emptyInstance.add("c"));

        // set
        assertEquals(0, emptyInstance.set("a", "b", 1));
        assertEquals(1, emptyInstance.set("a", "b", 2));
        assertEquals(2, emptyInstance.set("a", "b", 1));

        assertEquals(0, emptyInstance.set("c", "c", 1)); // same vertex
        assertEquals(0, emptyInstance.set("c", "c", 1));

        assertEquals(0, emptyInstance.set("a", "c", 4));
        assertEquals(4, emptyInstance.set("a", "c", 0));


        // sources & targets
        assertEquals(Collections.EMPTY_MAP, emptyInstance.sources("c"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.targets("c"));

    }

    @Rule
    public ExpectedException thrownNegW = ExpectedException.none();

    @Test
    public void testSetNegW() {
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        thrownNegW.expect(RuntimeException.class);
        thrownNegW.expectMessage("the weight is negative !");
        emptyInstance.set("a", "b", -2);
    }

    @Test
    public void testVertices() {
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        assertEquals(false, emptyInstance.add("b"));
        assertEquals(true, emptyInstance.add("c"));
        assertEquals(false, emptyInstance.remove("f"));
        assertEquals(true, emptyInstance.add("g"));
        assertEquals(true, emptyInstance.remove("g"));
        assertEquals(true, emptyInstance.add("g"));
        // vertices
        Set<String> vertices = new HashSet<String>();
        vertices.add("a");
        vertices.add("b");
        vertices.add("c");
        vertices.add("g");
        assertEquals(vertices, emptyInstance.vertices());
    }

    @Test
    public void testSourceTarget() {
        Graph<String> emptyInstance = emptyInstance();
        // add
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        assertEquals(true, emptyInstance.add("c"));
        assertEquals(true, emptyInstance.add("d"));

        // set
        assertEquals(0, emptyInstance.set("a", "b", 1));
        assertEquals(0, emptyInstance.set("a", "c", 3));
        assertEquals(0, emptyInstance.set("a", "d", 5));
        assertEquals(0, emptyInstance.set("b", "b", 1));
        assertEquals(0, emptyInstance.set("b", "b", 1));
        assertEquals(0, emptyInstance.set("b", "c", 2));
        assertEquals(0, emptyInstance.set("d", "a", 4));

        // source
        Map<String, Integer> sources = new HashMap<String, Integer>();
        sources.put("d", 4); // 4
        assertEquals(sources, emptyInstance.sources("a"));
        sources.remove("d"); // empty
        sources.put("a", 3); // 3
        sources.put("b", 2); // 2 3
        assertEquals(sources, emptyInstance.sources("c"));
        sources.clear(); // empty
        sources.put("a", 1); // 1
        assertEquals(sources, emptyInstance.sources("b"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.sources("e")); // sources of non-in-vertex should be empty

        // targets
        Map<String, Integer> targets = new HashMap<String, Integer>();
        targets.put("b", 1);
        targets.put("c", 3);
        targets.put("d", 5); // 2 3 4
        assertEquals(targets, emptyInstance.targets("a"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.targets("c"));
        targets.clear();
        targets.put("a", 4);
        assertEquals(targets, emptyInstance.targets("d"));
    }

    @Test
    public void testWhole() {
        Graph<String> emptyInstance = emptyInstance();

        // add
        assertEquals(true, emptyInstance.add("a"));
        assertEquals(false, emptyInstance.add("a"));
        assertEquals(true, emptyInstance.add("b"));
        assertEquals(true, emptyInstance.add("c"));
        assertEquals(true, emptyInstance.add("d"));

        // remove
        assertEquals(true, emptyInstance.remove("d"));
        assertEquals(false, emptyInstance.remove("A"));

        // set
        assertEquals(0, emptyInstance.set("a", "b", 1));
        assertEquals(1, emptyInstance.set("a", "b", 2));
        assertEquals(2, emptyInstance.set("a", "b", 1));

        // source
        Map<String, Integer> sources = new HashMap<String, Integer>();
        sources.put("a", 1);
        assertEquals(sources, emptyInstance.sources("b"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.sources("c"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.sources("d"));

        // targets
        Map<String, Integer> targets = new HashMap<String, Integer>();
        targets.put("b", 1);
        assertEquals(targets, emptyInstance.targets("a"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.targets("c"));
        assertEquals(Collections.EMPTY_MAP, emptyInstance.targets("e"));

        // vertices
        Set<String> vertices = new HashSet<String>();
        vertices.add("a");
        vertices.add("b");
        vertices.add("c");
        assertEquals(vertices, emptyInstance.vertices());
    }

}
