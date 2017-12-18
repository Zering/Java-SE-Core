package collection;

import java.util.Iterator;

/**
 * 集合类通用接口
 *
 * @author zhanghaojie Created by zhanghaojie on 2017/10/11.
 */
public interface Collection<E> extends Iterable<E> {

  /**
   * 集合类大小
   */
  int size();

  /**
   * 判断集合类是否为空
   */
  boolean isEmpty();

  /**
   * 判断是否包含元素o
   */
  boolean contains(Object o);

  /**
   * 是否包含另一个集合类的所有元素
   */
  boolean containsAll(Collection<? extends E> c);

  /**
   * 添加元素
   */
  boolean add(E element);

  /**
   * 添加另一个集合类的所有元素
   */
  boolean addAll(Collection<? extends E> c);

  /**
   * 移除特定元素
   */
  boolean remove(Object o);

  /**
   * 移除集合
   */
  boolean removeAll(Collection<? extends E> var1);

  /**
   * 清空集合
   */
  void clear();

  @Override
  Iterator<E> iterator();

  /**
   * 集合类的比较 需要重写
   */
  @Override
  int hashCode();

  /**
   * 集合类的比较 需要重写
   * 比较每一个元素
   */
  @Override
  boolean equals(Object o);
}
