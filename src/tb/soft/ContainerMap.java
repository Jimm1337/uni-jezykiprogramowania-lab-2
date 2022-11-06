package tb.soft;

import java.util.HashMap;
import java.util.Map;

// TODO: [Wymagania zadania] - Map
public class ContainerMap extends ContainerBase<Map<String, Person>> {
    public ContainerMap() {
        super(new HashMap<>());
    }

    @Override
    public void print() {
        System.out.println("Mapa osób:");
        for (var personEntry : container.entrySet()) {
            System.out.println(personEntry.getKey() + " -> " + personEntry.getValue());
        }
    }

    @Override
    public void add(Person p) {
        container.put(generateKey(p), p);
    }

    @Override
    public void delete(Person p) {
        final var key = generateKey(p);
        container.remove(key);
    }

    private String generateKey(Person p) {
        return p.toString();
    }
}
