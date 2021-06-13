package P2;
public class Person<L> {
    private final L name;

    public Person(L name)
    {
        this.name=name;
    }
    public L getname()
    {
        return this.name;
    }
}
