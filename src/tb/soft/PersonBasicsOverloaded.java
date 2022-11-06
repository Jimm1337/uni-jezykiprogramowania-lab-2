package tb.soft;

//TODO: [Wymagania zadania] - Klasa Person z przeładowanymi metodami equals i hashCode.
public class PersonBasicsOverloaded extends Person {
    public PersonBasicsOverloaded(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode() + birthYear + job.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PersonBasicsOverloaded otherPerson)) return false;

        return (this.firstName.equals(otherPerson.firstName) &&
                this.lastName.equals(otherPerson.lastName) &&
                this.birthYear == otherPerson.birthYear &&
                this.job == otherPerson.job);
    }
}
