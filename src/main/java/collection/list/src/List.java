package collection.list.src;

import collection.Collection;
import java.util.Iterator;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public interface List<E> extends Collection<E> {

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    boolean add(E element);

    void add(int index, E element);

    @Override
    boolean addAll(Collection<? extends E> c);

    @Override
    boolean remove(Object o);

    @Override
    void clear();

    @Override
    Iterator<E> iterator();

    @Override
    int hashCode();

    @Override
    boolean equals(Object o);
}
