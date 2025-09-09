package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> maxArrayComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        maxArrayComparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        Iterator<T> maxIterator = this.iterator();
        T currentMax = maxIterator.next();
        while (maxIterator.hasNext()) {
            T current = maxIterator.next();
            if (maxArrayComparator.compare(current, currentMax) > 0) {
                currentMax = current;
            }
        }
        return currentMax;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        Iterator<T> maxIterator = this.iterator();
        T currentMax = maxIterator.next();
        while (maxIterator.hasNext()) {
            T current = maxIterator.next();
            if (c.compare(current, currentMax) > 0) {
                currentMax = current;
            }
        }
        return currentMax;
    }

}