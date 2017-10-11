package collection;

/**
 * Created by zhanghaojie on 2017/10/11.
 */
public interface Iterator<E> {

  boolean hashnext();

  E next();

  void remove();
}
