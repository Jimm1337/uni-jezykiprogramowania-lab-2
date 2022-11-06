package tb.soft;

import java.io.*;


/*
 *  Program: Operacje na obiektach klasy Person
 *     Plik: Person.java
 *           definicja typu wyliczeniowego Job
 *           definicja klasy PersonException
 *           definicja publicznej klasy Person
 *
 *    Autor: Paweł Rogaliński
 *     Data:  październik 2018 r.
 */

/**
 * Klasa Person reprezentuje osoby, które są opisane za pomocą
 * czterech atrybutów: imię, nazwisko, rok urodzenia, stanowisko.
 * W klasie przyjęto ograniczenia:
 * - pola firstName oraz lastName muszą zawierać niepusty ciąg znaków
 * - pole birthYear musi zawierać liczbę z przedziału [1900-2030]
 * lub 0 (0 oznacza niezdefiniowany rok urodzenia).
 * - pole job musi zawierać wyłącznie jedną z pozycji zdefiniowanych
 * w typie wyliczeniowym enum PersonJob.
 * <p>
 * Powyższe ograniczenia są kontrolowane i w przypadku próby nadania
 * niedozwolonej wartości, któremuś z atrybutów jest zgłaszany wyjątek
 * zawierający stosowny komunikat.
 */
public class Person {

    protected String firstName;
    protected String lastName;
    protected int birthYear;
    protected PersonJob job;


    public Person(String first_name, String last_name) throws PersonException {
        setFirstName(first_name);
        setLastName(last_name);
        job = PersonJob.UNKNOWN;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String first_name) throws PersonException {
        if ((first_name == null) || first_name.equals(""))
            throw new PersonException("Pole <Imię> musi być wypełnione.");
        this.firstName = first_name;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String last_name) throws PersonException {
        if ((last_name == null) || last_name.equals(""))
            throw new PersonException("Pole <Nazwisko> musi być wypełnione.");
        this.lastName = last_name;
    }


    public int getBirthYear() {
        return birthYear;
    }


    public void setBirthYear(int birth_year) throws PersonException {
        if ((birth_year != 0) && (birth_year < 1900 || birth_year > 2030))
            throw new PersonException("Rok urodzenia musi być w przedziale [1900 - 2030].");
        this.birthYear = birth_year;
    }


    public void setBirthYear(String birth_year) throws PersonException {
        if (birth_year == null || birth_year.equals("")) {  // pusty łańcuch znaków oznacza rok niezdefiniowany
            setBirthYear(0);
            return;
        }
        try {
            setBirthYear(Integer.parseInt(birth_year));
        } catch (NumberFormatException e) {
            throw new PersonException("Rok urodzenia musi być liczbą całkowitą.");
        }
    }


    public PersonJob getJob() {
        return job;
    }


    public void setJob(PersonJob job) {
        this.job = job;
    }


    public void setJob(String job_name) throws PersonException {
        if (job_name == null || job_name.equals("")) {  // pusty łańcuch znaków oznacza stanowisko niezdefiniowane
            this.job = PersonJob.UNKNOWN;
            return;
        }
        for (PersonJob job : PersonJob.values()) {
            if (job.jobName.equals(job_name)) {
                this.job = job;
                return;
            }
        }
        throw new PersonException("Nie ma takiego stanowiska.");
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


    public static void printToFile(PrintWriter writer, Person person) {
        writer.println(person.firstName + "#" + person.lastName + "#" + person.birthYear + "#" + person.job);
    }


    public static void printToFile(String file_name, Person person) throws PersonException {
        try (PrintWriter writer = new PrintWriter(file_name)) {
            printToFile(writer, person);
        } catch (FileNotFoundException e) {
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        }
    }


    public static Person readFromFile(BufferedReader reader) throws PersonException {
        try {
            String line = reader.readLine();
            String[] txt = line.split("#");
            Person person = new Person(txt[0], txt[1]);
            person.setBirthYear(txt[2]);
            person.setJob(txt[3]);
            return person;
        } catch (IOException e) {
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }


    public static Person readFromFile(String file_name) throws PersonException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            return Person.readFromFile(reader);
        } catch (FileNotFoundException e) {
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        } catch (IOException e) {
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }

}  // koniec klasy Person
