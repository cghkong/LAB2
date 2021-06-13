/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteVerticesGraph()
    {

    }

    // TODO checkRep
    private void checkRep()
    {
        for(Vertex<L> vertex: vertices)
        {
            assert (vertex.getVer()!= null);
            Map<L, Integer> sources = vertex.getsources();
            for (Map.Entry<L, Integer> entry : sources.entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
            Map<L, Integer> targets = vertex.gettargets();
            for (Map.Entry<L, Integer> entry : targets.entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
        }
    }
    @Override public boolean add(L vertex) {
        for (Vertex<L> Ver : vertices) {
            if (vertex.equals(Ver.getVer()))
                return false;
        }
        Vertex<L> newVertex = new Vertex<L>(vertex);
        vertices.add(newVertex);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
         if(weight<0)
         {
             throw new RuntimeException("the weight is negative !");
         }
        if(!vertices.contains(target))
            this.add(target);
        if(!vertices.contains(source))
            this.add(source);
        if(source.equals(target))
            return 0;
        Vertex<L> from = null, to = null;
        for (Vertex<L> vertex : vertices) {
            if (source.equals(vertex.getVer()))
                from = vertex;
            if (target.equals(vertex.getVer()))
                to = vertex;
        }
        if (from == null || to == null)
            throw new NullPointerException("the vertex has already existed");
        int preEdgeWeight;
        if (weight > 0) {
            from.set_outEdge(target, weight);
            preEdgeWeight = to.set_inEdge(source, weight);
        } else {
            from.remove_outEdge(target);
            preEdgeWeight = to.remove_inEdge(source);
        }
        checkRep();
        return preEdgeWeight;
    }
    
    @Override public boolean remove(L vertex) {
        Iterator<Vertex<L>> iter= vertices.iterator();
        while (iter.hasNext())
        {
            Vertex<L> vert = iter.next();
            if(vertex.equals(vert.getVer()))
            {
                for (Vertex<L> v : vertices) {
                    if (vert.getsources().containsKey(v)) {
                        v.remove_outEdge(vert.getVer());
                    }
                    if (vert.gettargets().containsKey(v)) {
                        v.remove_inEdge(vert.getVer());
                    }
                }
                vertices.remove(vert);
                checkRep();
                return true;
            }
        }
        checkRep();
        return false;
    }
    
    @Override public Set<L> vertices() {
        Set<L> vertices_dup = new HashSet<>();
        for(Vertex<L> vert:vertices)
        {
            vertices_dup.add((L) vert.getVer());
        }
        checkRep();
        return vertices_dup;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> Source = new HashMap<>();
        for(Vertex<L> vert: vertices)
        {
            if(vert.getVer().equals(target))
            {
                Source.putAll(vert.getsources());
                break;
            }
        }
        checkRep();
        return Source;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> Target = new HashMap<>();
        for(Vertex<L> vert: vertices)
        {
            if(vert.getVer().equals(source))
            {
                Target.putAll(vert.gettargets());
                break;
            }
        }
        checkRep();
        return Target;
    }
    
    // TODO toString()
    @Override  public String toString()
    {
        return "the number of Vertices:"+vertices.size();
    }
}

