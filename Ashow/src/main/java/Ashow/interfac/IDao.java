package Ashow.interfac;

import java.util.List;

public interface IDao<T, K> {

    public T get(K k);

    public boolean add(T t);

    public boolean update(T t);

    public boolean remove(T t);

    public List<T> getAll();
}