package tb.soft;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// TODO: [Wymagania zadania] - Klasa Person z przeładowanymi metodami equals i hashCode.
public class PersonBasicsOverloaded extends Person {
    public PersonBasicsOverloaded(final String first_name, final String last_name) throws PersonException {
        super(first_name, last_name);
    }

    public PersonBasicsOverloaded(final Person currentPerson) {
        super(currentPerson);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += firstName.chars().sum();
        hash += lastName.chars().sum();
        hash += birthYear;
        hash += job.jobName.chars().sum();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PersonBasicsOverloaded otherPerson)) return false;

        return (this.firstName.equals(otherPerson.firstName) && this.lastName.equals(otherPerson.lastName));
    }

    public static Person readFromFile(final BufferedReader reader) throws PersonException {
        try {
            final String line = reader.readLine();
            final String[] txt = line.split("#");
            final Person person = new PersonBasicsOverloaded(txt[0], txt[1]);
            person.setBirthYear(txt[2]);
            person.setJob(txt[3]);
            return person;
        } catch (IOException e) {
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }

    public static Person readFromFile(final String file_name) throws PersonException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            return PersonBasicsOverloaded.readFromFile(reader);
        } catch (FileNotFoundException e) {
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        } catch (IOException e) {
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }
}
