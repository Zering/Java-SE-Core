package collection.list.src;

import collection.AbstractCollection;
import collection.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

  @Override
  public abstract E get(int index);

  @Override
  public E set(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E remove(int index) {
    return null;
  }

  @Override
  public void sort(Comparable<? extends E> comparable) {

  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean add(E element) {
    add(size(), element);
    return true;
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<? extends E> c) {
    return false;
  }

  @Override
  public void clear() {
    Iterator<E> iter = iterator();
    while (iter.hasNext()) {
      iter.next();
      iter.remove();
    }
  }

  @Override
  public int hashCode() {
    int hashcode = 1;
    for (E o : this) {
      hashcode = 31 * hashcode + (o == null ? 0 : o.hashCode());
    }
    return hashcode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof List)) {
      return false;
    }
    Iterator<E> o1 = this.iterator();
    Iterator o2 = ((List) obj).iterator();
    while (o1.hasNext() && o2.hasNext()) {
      E e1 = o1.next();
      Object e2 = o2.next();
      if (!(e1 == null ? e2 == null : e1.equals(e2))) {
        return false;
      }
    }
    return !(o1.hasNext() || o2.hasNext());
  }

  @Override
  public Iterator<E> iterator() {
    return new Itr();
  }

  private class Itr implements Iterator<E> {

    // 游标
    int cursor = 0;
    // 最后操作的索引 -1表示上一次是删除
    int lastRet = -1;

    @Override
    public boolean hasNext() {
      return cursor != size();
    }

    @Override
    public E next() {
      try {
        int i = cursor;
        E next = get(i);
        lastRet = i;
        cursor = i + 1;
        return next;
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }

    @Override
    public void remove() {
      try {
        AbstractList.this.remove(lastRet);
        if (lastRet < cursor) {
          cursor--;
        }
        lastRet = -1;
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }

  }

}
