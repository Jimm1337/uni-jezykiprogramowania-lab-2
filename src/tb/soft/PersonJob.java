package tb.soft;

/**
 * Typ wyliczeniowy PersonJob reprezentuje przykładowe stanowiska,
 * które może zajmować osoba. Klasa została zaimplementowana
 * tak, by mogła być rozszerzana o dodatkowe stanowiska.
 * W tym celu wystarczy do zdefiniowanej listy dodać kolejne
 * wywołanie konstruktora.
 */
enum PersonJob {
    UNKNOWN("-------"),
    GUEST("Gość"),
    STUDENT("Student"),
    TEACHER("Nauczyciel"),
    MANAGER("Kierownik"),
    DIRECTOR("Dyrektor");

    final String jobName;

    PersonJob(final String job_name) {
        jobName = job_name;
    }

    @Override
    public String toString() {
        return jobName;
    }
}  // koniec klasy enum Job