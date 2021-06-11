/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // test whether the returned String is matched with what we wanted

    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A1"));
        assertTrue(graph.add("A2"));
        assertFalse(graph.add("A1"));
        assertEquals(0, graph.set("A1", "A2", 1));
        assertEquals("the number of Vertices:2", graph.toString());
    }


    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    // vertex: two String value:the different ,the same,the null
    // weight:>0 =0 <0

    @Test
    public void testVertices() {
        Vertex<String> vertex1 = new Vertex<>("f1");
        Vertex<String> vertex2 = new Vertex<>("s1");
        try {
            vertex1.set_inEdge("s1", -1);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        try {
            vertex1.set_inEdge("f1", 0);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        vertex1.set_inEdge("s1", 1);
        vertex2.set_outEdge("f1", 2);
        HashMap<String, Integer> result1 = new HashMap<>();
        HashMap<String, Integer> result2 = new HashMap<>();
        result1.put("s1", 1);
        result2.put("f1", 2);
        assertEquals(result1, vertex1.getsources());
        assertEquals(new HashMap<>(), vertex1.gettargets());
        assertEquals(new HashMap<>(), vertex2.getsources());
        assertEquals(result2, vertex2.gettargets());
    }
    
}
