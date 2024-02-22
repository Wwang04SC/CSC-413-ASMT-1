import java.util.Iterator;
public interface ListWithIteratorInterface<E> extends ListInterface<E>, Iterable<E>
{
    public Iterator<E> getIterator();
}
