package P2;
import P1.graph.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class FriendshipGraph<L> {
    private final Graph<Person<L>> friendshipgraph = Graph.empty();

    // add a vertex
    public boolean addVertex(Person<L> person) {
        return friendshipgraph.add(person);
    }

    // add a edge
    public int addEdge(Person<L> a, Person<L> b) {
        int preweight = 0;
        preweight = friendshipgraph.set(a, b, 1);
        preweight = friendshipgraph.set(b, a, 1);
        return preweight;
    }

    // get the distance of two vertices
    public int getDistance(Person<L> sta, Person<L> end) {
        if (sta.equals(end))
            return 0;
        Map<Person<L>, Integer> dis = new HashMap<>();
        Map<Person<L>, Boolean> vis = new HashMap<>();
        Queue<Person<L>> queue = new LinkedList<Person<L>>();
        Set<Person<L>> persons = friendshipgraph.vertices();
        for (Person<L> person : persons) {
            dis.put(person, 0);
            vis.put(person, false);
        }
        vis.remove(sta);
        vis.put(sta, true);
        for (queue.offer(sta); !queue.isEmpty(); ) {
            Person<L> person = queue.poll();
            for (Map.Entry<Person<L>, Integer> edge : friendshipgraph.targets(person).entrySet()) {
                Person<L> target = edge.getKey();
                if (!vis.get(target)) {
                    queue.offer(target);
                    vis.remove(target);
                    vis.put(target, true);
                    dis.remove(target);
                    dis.put(target, dis.get(person) + 1);
                    if (target.equals(end))
                        return dis.get(target);
                }
            }
        }
        return -1;
    }

     /** Dijkstra,but some wrong exists
    public int getDistance(Person<L> source, Person<L> target)
    {

        if(source.equals(target))
            return 0;
        Map<Person<L>,Boolean> visit = new HashMap<>();
        Map<Person<L>,Integer> distance = new HashMap<>();
        Set<Person<L>> persons = friendshipgraph.vertices();
        for(Person<L> per : persons )
        {
            visit.put(per,true);
            distance.put(per, Integer.MAX_VALUE);
        }
        visit.remove(source);
        visit.put(source,false);
        for(int i=0;i<visit.size();i++)
        {
            int minvalue=Integer.MAX_VALUE;
            L name=source.getname();
            for(Map.Entry<Person<L>, Integer> mindis : distance.entrySet())
            {
                if((mindis.getValue()<minvalue) && visit.get(mindis.getKey()))
                {
                    minvalue=mindis.getValue();
                    name=mindis.getKey().getname();
                }
            }
            Person<L> per0 = new Person<>(name);
            visit.remove(per0);
            visit.put(per0,false);
            for(Map.Entry<Person<L>,Integer> edge : friendshipgraph.targets(per0).entrySet())
            {
                int newweight = edge.getValue()+distance.get(per0);
                if((newweight < distance.get(edge.getKey())))
                {
                    distance.remove(edge.getKey());
                    distance.put(edge.getKey(),newweight);
                }
            }
        }
        return distance.get(target);

    }
      */
}
