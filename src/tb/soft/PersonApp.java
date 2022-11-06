package tb.soft;

import java.util.Arrays;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 * operacji wykonywanych na obiektach klasy Person.
 * Plik: PersonApp.java
 * <p>
 * Autor: Paweł Rogaliński
 * Data: październik 2018 r.
 */

/**
 * 06.11.2022
 * Autor rozwiązania i modyfikacji: Oskar Gusta 263970
 */

public class PersonApp {

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
                    4 - Wczytaj dane z pliku
                    5 - Zapisz dane do pliku
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


    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() {
        UI.printMessage(GREETING_MESSAGE);

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
                    case 0 -> exit(); // zakończenie działania programu
                    default -> UI.printErrorMessage("Niepoprawny wybór!");
                }
            } catch (PersonException e) {
                UI.printErrorMessage(e.getMessage());
            }
        } // koniec pętli while
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
     */
    private static void showPerson(Person person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
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
    private static Person createNewPerson() {
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        Person person;
        try {
            person = new Person(first_name, last_name);
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
    private static void changePersonData(Person person) {
        while (true) {
            UI.clearConsole();
            showPerson(person);

            try {
                switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
                    case 1 -> person.setFirstName(UI.enterString("Podaj imię: "));
                    case 2 -> person.setLastName(UI.enterString("Podaj nazwisko: "));
                    case 3 -> person.setBirthYear(UI.enterString("Podaj rok ur.: "));
                    case 4 -> person.setJob(UI.enterString("Podaj stanowisko: "));
                    case 0 -> { return; }
                    default -> UI.printErrorMessage("Niepoprawny wybór!");
                }
            } catch (PersonException e) {
                UI.printErrorMessage(e.getMessage());
            }
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
        changePersonData(currentPerson);
    }


    private void currentPersonLoadFromFile() throws PersonException {
        String file_name = UI.enterString("Podaj nazwę pliku: ");
        currentPerson = Person.readFromFile(file_name);
        UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
    }


    private void currentPersonSave() throws PersonException {
        String file_name = UI.enterString("Podaj nazwę pliku: ");
        currentPerson = Person.readFromFile(file_name);
        UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
    }


    private void exit() {
        UI.printInfoMessage("\nProgram zakończył działanie!");
        System.exit(0);
    }

}  // koniec klasy PersonApp
