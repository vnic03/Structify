package structs;

import java.io.Serializable;
import java.util.Iterator;


public interface Structure<T> extends Iterable<T>, Cloneable, Serializable {

    boolean isEmpty();

    int size();

    void clear();

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException(
                "Iterator not supported for " + this.getClass().getName()
        );
    }
}
