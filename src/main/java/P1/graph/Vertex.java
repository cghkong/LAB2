package P1.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
public class Vertex<L> {

    // TODO fields
    private final L ver;
    private final Map<L, Integer> inEdges = new HashMap<>();
    private final Map<L, Integer> outEdges = new HashMap<>();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    public Vertex(L vertex)
    {
        this.ver=vertex;
    }

    // TODO constructor

    // TODO checkRep
    public void checkRep()
    {
        for (L key_it : inEdges.keySet())
            assert (inEdges.get(key_it) > 0);
        for (L key_it : outEdges.keySet())
            assert (outEdges.get(key_it) > 0);
    }


    // TODO methods
    public L getVer()
    {
        return this.ver;
    }

    public Map<L, Integer> getsources() {
        Map<L, Integer> sources = new HashMap<>();
        sources.putAll(inEdges); // 深拷贝
        return sources;
    }

    public Map<L, Integer> gettargets() {
        Map<L, Integer> targets = new HashMap<>();
        targets.putAll(outEdges); // 深拷贝
        return targets;
    }
    public int set_outEdge(L target, int weight)
    {
        if(weight<=0)
            return 0;
        Iterator<L> iter = outEdges.keySet().iterator();
        while (iter.hasNext())
        {
            L key_it = iter.next();
            if(key_it.equals(target))
            {
                int preweight = outEdges.get(key_it);
                iter.remove();
                outEdges.put(target,weight);
                checkRep();
                return preweight;
            }
        }
        outEdges.put(target,weight);
        checkRep();
        return 0;
    }

    public int set_inEdge(L source, int weight)
    {
        if(weight<=0)
            return 0;
        Iterator<L> iter = inEdges.keySet().iterator();
        while (iter.hasNext())
        {
            L key_it = iter.next();
            if(key_it.equals(source))
            {
                int preweight = inEdges.get(key_it);
                iter.remove();
                inEdges.put(source,weight);
                checkRep();
                return preweight;
            }
        }
        inEdges.put(source,weight);
        checkRep();
        return 0;

    }

    public int remove_outEdge(L target)
    {
        if(!outEdges.containsKey(target))
        {
            return 0;
        }
        int preweight=outEdges.get(target);
        outEdges.remove(target);
        checkRep();
        return preweight;
    }

    public int remove_inEdge(L source)
    {
        if(!inEdges.containsKey(source))
        {
            return 0;
        }
        int preweight=inEdges.get(source);
        inEdges.remove(source);
        checkRep();
        return preweight;

    }

    @Override public String toString()
    {
        return " ver: "+this.ver;
    }


}
