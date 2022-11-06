package tb.soft;

// Klasa bazowa na różne kontenery, posiada instancje kontenera
abstract class ContainerBase<ContainerType> {
    // Instancja kontenera
    protected ContainerType container;

    ContainerBase(ContainerType containerInit) {
        container = containerInit;
    }

    // Wypisuje zawartość kontenera
    abstract public void print(); // TODO: [Wymagania zadania] - Wypisywanie i iteracja.

    // Dodaje element do kontenera
    abstract public void add(Person p); //TODO: [Wymagania zadania] - Dodawanie elementów.

    // Usuwa element z kontenera
    abstract public void delete(Person p); //TODO: [Wymagania zadania] - Usuwanie elementów.
}
