/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    ConcreteEdgesGraph()
    {

    }

    // TODO checkRep
    private void checkRep()
    {
        for(L ver: vertices)
        {
            assert (ver!=null);
        }
        for(Edge<L> edge: edges)
        {
            assert (edge!=null);
        }
    }

    
    @Override public boolean add(L vertex) {
        if(vertices.contains(vertex))
            return false;
        vertices.add(vertex);
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        if(weight<0)
            throw new RuntimeException("the weight is negative !");
        if(!vertices.contains(target))
            vertices.add(target);
        if(!vertices.contains(source))
            vertices.add(source);
        if(source.equals(target))
            return 0;
        Iterator<Edge<L>> iter = edges.iterator();
        while(iter.hasNext())
        {
            Edge<L> edge1 = iter.next();
            if(edge1.equaledge(source,target))
            {
                int preEdgeWeight = edge1.getWeight();
                iter.remove();
                if(weight>0)
                {
                    Edge<L> edge2= new Edge<>(source,target,weight);
                    edges.add(edge2);
                }
                checkRep();
                return preEdgeWeight;
            }
        }
        if(weight>0)
        {
            Edge<L> edge2= new Edge<>(source,target,weight);
            edges.add(edge2);
        }
        checkRep();
        return 0;
    }

    @Override public boolean remove(L vertex) {
        /** 遍历方式remove
          if(vertices.contains(vertex))
          {
            Iterator<Edge<L>> iter = edges.iterator();
            while(iter.hasNext())
            {
                Edge<L> edge1 = iter.next();
                if(vertex.equals(edge1.getSource())||vertex.equals(edge1.getTarget()))
                {
                    iter.remove();
                }
            }
            vertices.remove(vertex);
            return true;
        }
        return false;
         */
        if (!vertices.contains(vertex))
            return false;
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        vertices.remove(vertex);
        checkRep();
        return true;
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> source1=new HashMap<>();
        for(Edge<L> edge1: edges)
        {
            if(target.equals(edge1.getTarget()))
            {
                source1.put((L) edge1.getSource(),edge1.getWeight());
            }
        }
        checkRep();
        return source1;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> target1 = new HashMap<>();
        for(Edge<L> edge1: edges)
        {
            if(source.equals(edge1.getSource()))
            {
                target1.put((L) edge1.getTarget(),edge1.getWeight());
            }
        }
        checkRep();
        return target1;
    }
    
    // TODO toString()
    @Override
    public String toString()
    {
        return "the number of vertices is "+vertices.size()+"    the number of edges is "+edges.size();
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L source,target;
    private final int weight;

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    Edge(L source, L target, int weight)
    {
        this.source=source;
        this.target=target;
        this.weight=weight;
        checkRep();
    }

    // TODO checkRep
    private void checkRep()
    {
         assert ((weight>0)&&(source.equals(target)));
    }

    // TODO methods
    public int getWeight()
    {
        return this.weight;
    }
    public L getSource() { return this.source; }
    public L getTarget()
    {
        return this.target;
    }
    public boolean equaledge(L source,L target)
    {
        if(source.equals(this.source) && target.equals(this.target))
            return true;
        return false;
    }

    // TODO toString()
    @Override
    public String toString()
    {
        return "the source is "+this.source+" ,the target is "+this.target+" ,the weight is "+this.weight;
    }
}
