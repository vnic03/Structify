package structs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;


public interface Structure<T> extends Iterable<T>, Cloneable, Serializable {

    boolean isEmpty();

    int size();

    void clear();

    default Structure<T> filter(Predicate<T> predicate) {
        throw new UnsupportedOperationException(
                "filter method not supported for " + this.getClass().getName()
        );
    }

    default <M> Structure<M> map(Function<T, M> mapper) {
        throw new UnsupportedOperationException(
                "map method not supported for " + this.getClass().getName()
        );
    }

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException(
                "Iterator not supported for " + this.getClass().getName()
        );
    }
}
