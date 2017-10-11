package collection.list.src;

import collection.Collection;
import collection.Iterable;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public interface List<E> extends Collection<E> {

  E get(int index);

  E remove(int index);

  void sort(Comparable<? extends E> comparable);

  @Override
  int size();

  @Override
  boolean isEmpty();

  @Override
  boolean contains();

  @Override
  boolean containsAll(Collection<?> c);

  @Override
  boolean add(E element);

  @Override
  boolean addAll(Collection<? extends E> c);

  @Override
  boolean remove(Object o);

  @Override
  boolean removeAll(Collection<?> c);

  @Override
  void clear();

  @Override
  Iterable<E> iterator();

  @Override
  int hashCode();

  @Override
  boolean equals(Object o);
}
