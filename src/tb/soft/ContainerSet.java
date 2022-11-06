package tb.soft;

import java.util.Set;
import java.util.function.Supplier;

// TODO: [Wymagania zadania] - Set
public class ContainerSet<SetType extends Set<Person>> extends ContainerBase<SetType> {
    // Używanie: new ContainerSet<>('implementacja interfejsu Set'<Person>::new)
    // Przykład: new ContainerSet<>(TreeSet<Person>::new)
    ContainerSet(Supplier<? extends SetType> supplier) {
        super(supplier);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Zbiór osób:\n");
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
        container.remove(p);
    }
}
