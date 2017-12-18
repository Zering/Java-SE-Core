package collection.list.src;

import java.util.Arrays;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public class ArrayList<E> extends AbstractList<E> implements List<E> {

  private final static int DEFAULT_CAPACITY = 10;

  private Object[] elementData;

  private Object[] EMPTY_DATA = {};

  private int size;

  public ArrayList() {
    super();
    this.elementData = EMPTY_DATA;
  }

  @SuppressWarnings("unchecked")
  private E elementData(int index) {
    return (E) elementData[index];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (elementData[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (o.equals(elementData[i])) {
          return i;
        }
      }
    }

    return -1;
  }

  private void checkRange(int index) {
    if (index > size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public E get(int index) {
    checkRange(index);
    return elementData(index);
  }

  @Override
  public E set(int index, E element) {
    checkRange(index);

    E oldValue = elementData(index);
    elementData[index] = element;

    return oldValue;
  }

  @Override
  public boolean add(E element) {
    ensureCapacity(size + 1);
    return super.add(element);
  }

  /**
   * 检查容量
   */
  private void ensureCapacity(int minCap) {
    if (elementData == EMPTY_DATA) {
      minCap = Math.min(DEFAULT_CAPACITY, minCap);
    }

    if (elementData.length <= minCap) {
      grow(minCap);
    }
  }

  private void grow(int minCap) {
    int oldCap = elementData.length;
    int newCap = oldCap + oldCap >> 1;
    if (newCap < minCap) {
      newCap = minCap;
    }
    if (newCap < 0) { // 超出int值
      throw new OutOfMemoryError();
    }

    elementData = Arrays.copyOf(elementData, newCap);
  }

  @Override
  public E remove(int index) {
    checkRange(index);
    E oldValue = elementData(index);

    // 前移其后的所有数据
    int numMove = size - 1 - index;
    if (numMove > 0) {
      System.arraycopy(elementData, index + 1, elementData, index, numMove);
    }
    elementData[--size] = null;

    return oldValue;
  }
}