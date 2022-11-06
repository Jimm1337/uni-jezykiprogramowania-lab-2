package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 * operacji wykonywanych na obiektach klasy Person.
 * Plik: PersonApp.java
 * <p>
 * Autor: Paweł Rogaliński
 * Data: październik 2018 r.*
 * <p>
 * <p>
 * 06.11.2022
 * Autor rozwiązania i modyfikacji: Oskar Gusta 263970
 */
public class PersonApp {

    private static final boolean PRINT_INFO_MESSAGES = true; // czy wypisywać tłumaczenie opcji na początku

    private static final String GREETING_MESSAGE =
            """
                    Program PersonApp
                    Autor: Oskar Gusta
                    Data:  06.11.2022 r.
                    """;

    private static final String MENU =
            """
                    M E N U   G Ł Ó W N E
                    1 - Podaj dane nowej osoby
                    2 - Usuń dane osoby
                    3 - Modyfikuj dane osoby
                    4 - Wczytaj dane osoby z pliku
                    5 - Zapisz dane osoby do pliku
                    6 - Dodaj do kolekcji
                    7 - Usuń z kolekcji
                    8 - Wypisz kolekcję
                    0 - Zakończ program
                    """;

    private static final String CHANGE_MENU =
            """
                    Co zmienić?
                    1 - Imię
                    2 - Nazwisko
                    3 - Rok urodzenia
                    4 - Stanowisko
                    0 - Powrót do menu głównego
                    """;

    private static final IUserDialog UI = new UserDialogJOption();

    private Person currentPerson = null;
    private boolean useOverloadedPerson = false;
    private ContainerBase<?> container;

    //komparatory do sortowania kolekcji typu tree
    Comparator<Person> comparatorSet = Comparator.comparing(Person::hashCode);
    Comparator<Integer> comparatorMap = Comparator.comparing(Integer::intValue);

    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() {
        UI.printMessage(GREETING_MESSAGE);

        if (PRINT_INFO_MESSAGES) printInfoPersonImplementation();
        askForPersonImplementation(); // TODO: [Zadanie] - rozgraniczenie na Person i OverloadedPerson
        if (PRINT_INFO_MESSAGES) printInfoComparators();
        askForComparator(); // TODO: [Zadanie] - wybór komparatora
        if (PRINT_INFO_MESSAGES) printInfoCollectionImplementation();
        askForCollectionImplementation(); // TODO: [Zadanie] - wybór typu kolekcji

        while (true) {
            UI.clearConsole();
            currentPersonShow();

            try {
                switch (UI.enterInt(MENU + "==>> ")) {
                    case 1 -> currentPersonCreate(); // utworzenie nowej osoby
                    case 2 -> currentPersonDelete(); // usunięcie danych aktualnej osoby.
                    case 3 -> currentPersonChange(); // zmiana danych dla aktualnej osoby
                    case 4 -> currentPersonLoadFromFile(); // odczyt danych z pliku tekstowego.
                    case 5 -> currentPersonSave(); // zapis danych aktualnej osoby do pliku tekstowego
                    case 6 -> currentPersonAddToCollection(); // TODO: [Zadanie] - dodanie osoby do kolekcji
                    case 7 -> currentPersonRemoveFromCollection(); // TODO: [Zadanie] - usunięcie osoby z kolekcji
                    case 8 -> printCollection(); // TODO: [Zadanie] - wyświetlenie kolekcji
                    case 0 -> exit(); // zakończenie działania programu
                    default -> UI.printErrorMessage("Niepoprawny wybór!");
                }
            } catch (PersonException e) {
                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }

    //info o Comparatorach i Comparable
    private void printInfoComparators() {
        UI.printInfoMessage("""
                Comparator to interfejs funkcyjny, który posiada jedną metodę compare, która przyjmuje dwa argumenty i zwraca liczbę całkowitą.
                    - Implementacja w osobnych klasach pomocniczych albo korzystanie z buildera w interfejsie Comparator.
                    - Przekazywane w konstruktorze kolekcji.
                    
                Comparable to interfejs który posiada jedną metodę compareTo, która przyjmuje jeden argument i zwraca liczbę całkowitą.
                    - Implementacja w klasie.
                    
                (Implementacja) Domyślny Comparator:
                    - Dla Set: hashCode rosnąco.
                    - Dla Map: hashCode klucza rosnąco.
                    
                (Implementacja) Alternatywny Comparator:
                    - Dla Set: Nazwisko i imie alfabetycznie.
                    - Dla Map: hashCode klucza malejąco.
                """);
    }

    //pyta czy użyć alternatywnego komparatora
    private void askForComparator() {
        char answer;
        do {
            answer = UI.enterChar("Używać alternatywnego Komparatora dla typów Tree? [y/n]:");
            answer = Character.toLowerCase(answer);

            if (answer != 'y' && answer != 'n') {
                UI.printErrorMessage("Niepoprawny wybór!");
            }
        } while (answer != 'y' && answer != 'n');

        if (answer == 'y') {
            comparatorSet = new ContainerSetAlternativeComparator();
            comparatorMap = new ContainerMapAlternativeComparator();
        }
    }

    //info o implementacjach Person
    private void printInfoPersonImplementation() {
        UI.printInfoMessage("""
                Implementacje klasy Person:
                Przeładowana - przeładowane metody hashCode i equals.
                Zwykła - metody hashCode i equals z klasy Object. (Usuwanie nie działa poprawnie dla typów Set i Map)
                """);
    }

    //pyta czy używać przeładowanej klasy
    private void askForPersonImplementation() {
        char answer;
        do {
            answer = UI.enterChar("Używać klasy z przeładowanym equals i hashCode? [y/n]:");
            answer = Character.toLowerCase(answer);

            if (answer != 'y' && answer != 'n') {
                UI.printErrorMessage("Niepoprawny wybór!");
            }
        } while (answer != 'y' && answer != 'n');
        useOverloadedPerson = answer == 'y';
    }

    //info o typach kolekcji dostępnych do wyboru
    private void printInfoCollectionImplementation() {
        UI.printInfoMessage("""
                Właściwości kolekcji:
                ArrayList - (w kolejności dodawania, z powtórzeniami).
                TreeSet - (sortowana, bez powtórzeń).
                HashSet - (nieokreślona kolejność, bez powtórzeń)
                TreeMap - (sortowana kluczami, bez powtórzeń kluczy) (Implementacja: klucz to hashCode).
                HashMap - (nieokreślona kolejność, bez powtórzeń kluczy) (Implementacja: klucz to hashCode).
                """);
    }

    //pyta i inicjalizuje kolekcję
    private void askForCollectionImplementation() {
        int answer;

        //wybór implementacji
        do {
            answer = UI.enterInt("""
                    Wybierz implementację kolekcji:
                    1 - ArrayList
                    2 - TreeSet
                    3 - HashSet
                    4 - TreeMap
                    5 - HashMap
                    """);

            if (answer < 1 || answer > 6) {
                UI.printErrorMessage("Niepoprawny wybór!");
            }
        } while (answer < 1 || answer > 6);

        //inicjalizacja
        switch (answer) {
            case 1 -> container = new ContainerList<>(ArrayList::new);
            case 2 -> container = new ContainerSet<>(() -> new TreeSet<>(comparatorSet));
            case 3 -> container = new ContainerSet<>(HashSet::new);
            case 4 -> container = new ContainerMap<>(() -> new TreeMap<>(comparatorMap));
            case 5 -> container = new ContainerMap<>(HashMap::new);
        }
    }

    private void currentPersonShow() {
        showPerson(currentPerson);
    }

    private void currentPersonCreate() {
        currentPerson = createNewPerson();
    }

    private void currentPersonDelete() {
        currentPerson = null;
        UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
    }

    private void currentPersonChange() throws PersonException {
        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");

        // potrzebne do rekalkulacji hashCode
        if (useOverloadedPerson) {
            currentPerson = new PersonBasicsOverloaded(currentPerson);
        } else {
            currentPerson = new Person(currentPerson);
        }

        changePersonData(currentPerson);
    }

    private void currentPersonLoadFromFile() throws PersonException {
        final String file_name = UI.enterString("Podaj nazwę pliku: ");
        if (useOverloadedPerson) {
            currentPerson = PersonBasicsOverloaded.readFromFile(file_name);
        } else {
            currentPerson = Person.readFromFile(file_name);
        }
        UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
    }

    private void currentPersonSave() throws PersonException {
        final String file_name = UI.enterString("Podaj nazwę pliku: ");
        Person.printToFile(file_name, currentPerson);
        UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
    }

    private void currentPersonAddToCollection() throws PersonException {
        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
        container.add(currentPerson);
        UI.printInfoMessage("Osoba została dodana do kolekcji");
    }

    private void currentPersonRemoveFromCollection() throws PersonException {
        if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
        container.remove(currentPerson);
        UI.printInfoMessage("Osoba została usunięta z kolekcji");
    }

    private void printCollection() {
        UI.printMessage(container.toString());
    }

    private void exit() {
        UI.printInfoMessage("\nProgram zakończył działanie!");
        System.exit(0);
    }

    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    private static void showPerson(final Person person) {
        final StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("Imię: ").append(person.getFirstName()).append("\n")
                    .append("Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append("Brak danych osoby\n");
        UI.printMessage(sb.toString());
    }

    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    private Person createNewPerson() {
        final String first_name = UI.enterString("Podaj imię: ");
        final String last_name = UI.enterString("Podaj nazwisko: ");
        final String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        final String job_name = UI.enterString("Podaj stanowisko: ");
        final Person person;
        try {
            if (useOverloadedPerson) {
                person = new PersonBasicsOverloaded(first_name, last_name);
            } else {
                person = new Person(first_name, last_name);
            }
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) { // Wychwycenie błędu gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        return person;
    }

    /*
     * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów
     * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
     * Walidacja poprawności wczytanych danych odbywa się w setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */
    private static void changePersonData(final Person person) {
        while (true) {
            UI.clearConsole();
            showPerson(person);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1 -> person.setFirstName(UI.enterString("Podaj imię: "));
                    case 2 -> person.setLastName(UI.enterString("Podaj nazwisko: "));
                    case 3 -> person.setBirthYear(UI.enterString("Podaj rok ur.: "));
                    case 4 -> person.setJob(UI.enterString("Podaj stanowisko: "));
                    case 0 -> {
                        return;
                    }
                    default -> UI.printErrorMessage("Niepoprawny wybór!");
                }
            } catch (PersonException e) {
                UI.printErrorMessage(e.getMessage());
            }
        }
    }
}  // koniec klasy PersonApp
