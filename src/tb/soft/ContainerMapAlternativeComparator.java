package tb.soft;

import java.util.Comparator;

public class ContainerMapAlternativeComparator implements Comparator<Integer> {
    @Override
    public int compare(final Integer o1, final Integer o2) {
        return Comparator.comparing(Integer::intValue).reversed().compare(o1, o2);
    }
}
