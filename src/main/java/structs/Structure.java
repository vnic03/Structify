package structs;

import java.io.Serializable;
import java.util.Iterator;


public interface Structure<T> extends Iterable<T>, Cloneable, Serializable {

    boolean isEmpty();

    int size();

    void clear();

    @Override
    Iterator<T> iterator();
}
