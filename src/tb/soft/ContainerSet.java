package tb.soft;

import java.util.Set;
import java.util.TreeSet;

// TODO: [Wymagania zadania] - Set
public class ContainerSet extends ContainerBase<Set<Person>> {
    public ContainerSet() {
        super(new TreeSet<>());
    }

    @Override
    public void print() {
        System.out.println("Zbiór osób:");
        for (var person : container) {
            System.out.println("- " + person);
        }
    }

    @Override
    public void add(Person p) {
        container.add(p);
    }

    @Override
    public void delete(Person p) {
        container.remove(p);
    }
}
