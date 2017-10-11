package collection;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public interface Collection<E> extends Iterable<E> {

  int size();

  boolean isEmpty();

  boolean contains();

  boolean containsAll(Collection<?> c);

  boolean add(E element);

  boolean addAll(Collection<? extends E> c);

  boolean remove(Object o);

  boolean removeAll(Collection<?> c);

  void clear();


  @Override
  Iterable<E> iterator();

  @Override
  int hashCode();

  @Override
  boolean equals(Object o);
}
