/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;
import P1.graph.*;
import P1.graph.ConcreteVerticesGraph.*;
import P1.graph.ConcreteEdgesGraph.*;
import P1.graph.Graph;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // at the same time ,this also test the add function and set function
    @Test
    public void testToString() {
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("A"));
        assertEquals(true, emptyInstance.add("B"));
        assertEquals(true, emptyInstance.add("C"));
        assertEquals(false, emptyInstance.add("A"));
        assertEquals(0, emptyInstance.set("A", "B", 2));
        assertEquals(0, emptyInstance.set("A", "C", 3));
        assertEquals(0, emptyInstance.set("A", "A", 2));
        assertEquals("the number of vertices is 3,the number of edges is 2", emptyInstance.toString());
    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // vertex: two String value:the different ,the same,the null
    // weight:>0 =0 <0

    @SuppressWarnings("unused")
    @Test
    public void testopration() {
        // cover vertex :two same String
        // cover weight<0
        try {
            Edge<String> e1 = new Edge<>("f1", "f1", -1);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        // cover vertex:two different String
        // cover weight = 0
        try {
            Edge<String> e2 = new Edge<>("f1", "s1", 0);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        // cover weight>0
        Edge<String> e3 = new Edge<>("f1", "s2", 1);
        assertEquals("f1", e3.getSource());
        assertEquals("s2", e3.getTarget());
        assertEquals(1, e3.getWeight());
    }

}
