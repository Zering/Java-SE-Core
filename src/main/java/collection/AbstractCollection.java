package collection;

import java.util.Iterator;

/**
 * Created by zhanghaojie on 2017/10/17.
 */
public abstract class AbstractCollection<E> implements Collection<E> {

  /**
   * 集合类迭代器
   */
  @Override
  public abstract Iterator<E> iterator();

  /**
   * 集合类的大小
   */
  @Override
  public abstract int size();

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    Iterator<E> iterator = iterator();
    if (o == null) {
      while (iterator.hasNext()) {
        if (iterator.next() == null) {
          return true;
        }
      }
    } else {
      while (iterator.hasNext()) {
        if (o.equals(iterator.next())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 默认不支持添加
   */
  @Override
  public boolean add(E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    for (E o : c) {
      if (add(o)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean remove(Object o) {
    Iterator<E> iterator = iterator();
    if (o == null) {
      while (iterator.hasNext()) {
        if (iterator.next() == null) {
          iterator.remove();
          return true;
        }
      }
    } else {
      while (iterator.hasNext()) {
        if (o.equals(iterator.next())) {
          iterator.remove();
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<? extends E> c) {
    for (Object o : c) {
      if (!contains(o)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean removeAll(Collection<? extends E> c) {
    boolean modified = false;
    for (Object o : c) {
      if (remove(o)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public void clear() {
    Iterator<E> iter = iterator();
    while (iter.hasNext()) {
      iter.next();
      iter.remove();
    }
  }

}
