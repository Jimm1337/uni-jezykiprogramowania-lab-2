package tb.soft;

import java.util.ArrayList;
import java.util.List;

// TODO: [Wymagania zadania] - List
public class ContainerList extends ContainerBase<List<Person>> {
    public ContainerList() {
        super(new ArrayList<>());
    }

    @Override
    public void print() {
        System.out.println("Lista osób:");
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
        while (container.contains(p)) {
            container.remove(p);
        }
    }
}
