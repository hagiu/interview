import java.util.Collection;

/**
 * Created by root on 02.03.2015.
 */
public interface LinkedList<T> {

    boolean add(T element);

    int size();

    boolean addAll(Collection<? extends T> source);
}
