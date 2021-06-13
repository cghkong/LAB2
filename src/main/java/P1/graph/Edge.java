package P1.graph;

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
public class Edge<L> {

    // TODO fields
    private final L source, target;
    private final int weight;

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor
    public Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {
        assert ((weight > 0) && (!source.equals(target)));
    }

    // TODO methods
    public int getWeight() {
        return this.weight;
    }

    public L getSource() {
        return this.source;
    }

    public L getTarget() {
        return this.target;
    }

    public boolean equaledge(L source, L target) {
        if (source.equals(this.source) && target.equals(this.target))
            return true;
        return false;
    }

    // TODO toString()
    @Override
    public String toString() {
        return "the source is " + this.source + " ,the target is " + this.target + " ,the weight is " + this.weight;
    }
}
