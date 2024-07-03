package structs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public interface Structure<T> extends Iterable<T>, Cloneable, Serializable {

    void add(T element);

    boolean isEmpty();

    int size();

    void clear();

    default Stream<T> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator(), 0),
                false);
    }

    static <T, S extends Structure<T>> Collector<T, ?, S> collector(Supplier<S> supplier) {
        return Collector.of(
                supplier,
                Structure::add,
                (left, right) -> {
                    right.forEach(left::add);
                    return left;
                }
        );
    }

    @Override
    default Iterator<T> iterator() {
        throw new UnsupportedOperationException(
                "Iterator not supported for " + this.getClass().getName()
        );
    }
}
