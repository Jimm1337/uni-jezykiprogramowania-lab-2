package tb.soft;

import java.util.Comparator;

public class ContainerSetAlternativeComparator implements Comparator<Person> {
    @Override
    public int compare(final Person o1, final Person o2) {
        return Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).compare(o1, o2);
    }
}
