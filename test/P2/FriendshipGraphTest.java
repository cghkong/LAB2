package P2;



import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

    /**
     * esstensial Network Test
     */
    @Test
    public void teste1() {
        final FriendshipGraph graph = new FriendshipGraph();

        final Person rachel = new Person("Rachel");
        final Person ross = new Person("Ross");
        final Person ben = new Person("Ben");
        final Person kramer = new Person("Kramer");

        assertEquals(true, graph.addVertex(rachel));
        assertEquals(true, graph.addVertex(ross));
        assertEquals(true, graph.addVertex(ben));
        assertEquals(true, graph.addVertex(kramer));

        assertEquals(0, graph.addEdge(rachel, ross));
        assertEquals(1, graph.addEdge(ross, rachel));
        assertEquals(0, graph.addEdge(ben, ross));
        assertEquals(1, graph.addEdge(ben, ross));

        assertEquals(2, graph.getDistance(rachel, ben));
        assertEquals(1, graph.getDistance(rachel, ross));
        assertEquals(0, graph.getDistance(rachel, rachel));
        assertEquals(-1, graph.getDistance(rachel, kramer));
    }

    /**
     * flexible Test
     */
    @Test
    public void testf2() {
        final FriendshipGraph graph = new FriendshipGraph();

        final Person a = new Person("1");
        final Person b = new Person("2");
        final Person c = new Person("3");
        final Person d = new Person("4");
        final Person e = new Person("5");
        final Person f = new Person("6");
        final Person g = new Person("7");
        final Person h = new Person("8");

        assertEquals(true, graph.addVertex(a));
        assertEquals(true, graph.addVertex(b));
        assertEquals(true, graph.addVertex(c));
        assertEquals(true, graph.addVertex(d));
        assertEquals(true, graph.addVertex(e));
        assertEquals(true, graph.addVertex(f));
        assertEquals(true, graph.addVertex(g));
        assertEquals(true, graph.addVertex(h));

        assertEquals(0, graph.addEdge(a, b));
        assertEquals(0, graph.addEdge(a, d));
        assertEquals(0, graph.addEdge(b, d));
        assertEquals(0, graph.addEdge(c, d));
        assertEquals(0, graph.addEdge(d, e));
        assertEquals(0, graph.addEdge(c, f));
        assertEquals(0, graph.addEdge(e, g));
        assertEquals(0, graph.addEdge(f, g));

        assertEquals(2, graph.getDistance(a, e));
        assertEquals(1, graph.getDistance(a, d));
        assertEquals(3, graph.getDistance(a, g));
        assertEquals(3, graph.getDistance(b, f));
        assertEquals(2, graph.getDistance(d, f));
        assertEquals(-1, graph.getDistance(f, h));
    }

}
