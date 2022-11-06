package tb.soft;

import java.util.Map;
import java.util.function.Supplier;

// TODO: [Wymagania zadania] - Map
public class ContainerMap<MapType extends Map<Integer, Person>> extends ContainerBase<MapType> {
    // Używanie: new ContainerMap<>('implementacja interfejsu Map'<String, Person>::new)
    // Przykład: new ContainerMap<>(HashMap<String, Person>::new)
    ContainerMap(Supplier<? extends MapType> supplier) {
        super(supplier);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Mapa osób:\n");
        for (var personEntry : container.entrySet()) {
            sb.append(personEntry.getKey()).append(" -> ").append(personEntry.getValue()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public void add(final Person p) {
        container.put(getKey(p), p);
    }

    @Override
    public void remove(final Person p) {
        container.remove(getKey(p));
    }

    private Integer getKey(final Person p) {
        return p.hashCode();
    }
}
