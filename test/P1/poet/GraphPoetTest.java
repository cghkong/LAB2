/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import org.junit.Test;


/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void test1() throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/corpus1.txt"));
        final String input = "I like you today";
        String output = nimoy.poem(input);
        System.out.println(input + "\n>>>\n" + output);
        assertEquals("I like to you to today", output);
    }

    @Test
    public void test2() throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/corpus2.txt"));
        final String input = "you look so nice";
        String output = nimoy.poem(input);
        System.out.println(input + "\n>>>\n" + output);
        assertEquals("you nice look so you nice", output);
    }
}
