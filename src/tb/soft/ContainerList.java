package tb.soft;

import java.util.List;
import java.util.function.Supplier;

// TODO: [Wymagania zadania] - List
public class ContainerList<ListType extends List<Person>> extends ContainerBase<ListType> {
    // Używanie: new ContainerList<>('implementacja interfejsu List'<Person>::new)
    // Przykład: new ContainerList<>(ArrayList<Person>::new)
    ContainerList(Supplier<? extends ListType> supplier) {
        super(supplier);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Lista osób:\n");
        for (var person : container) {
            sb.append("- ").append(person).append("\n");
        }

        return sb.toString();
    }

    @Override
    public void add(final Person p) {
        container.add(p);
    }

    @Override
    public void remove(final Person p) {
        while (container.contains(p)) {
            container.remove(p);
        }
    }
}
