package tb.soft;

import java.util.Objects;
import java.util.function.Supplier;

// Klasa bazowa na różne kontenery, posiada instancje kontenera
abstract class ContainerBase<ContainerType> {
    // instancja kontenera
    protected final ContainerType container;

    // TODO: [Powód] - Czemu takie rozwiązanie (Supplier) -> nie można zrobić tak: container = new ContainerType();

    // pobiera konstruktor z wybranego typu kontenera i go inicjalizuje
    ContainerBase(Supplier<? extends ContainerType> supplier) {
        container = Objects.requireNonNull(supplier).get();
    }

    // zawartość kontenera do stringa
    abstract public String toString(); // TODO: [Wymagania zadania] - Wypisywanie i iteracja.

    // Dodaje element do kontenera
    abstract public void add(final Person p); //TODO: [Wymagania zadania] - Dodawanie elementów.

    // Usuwa element z kontenera
    abstract public void remove(final Person p); //TODO: [Wymagania zadania] - Usuwanie elementów.
}
