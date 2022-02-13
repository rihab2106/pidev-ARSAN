package Controller;

import java.util.List;

public interface Idoa<T> {
    void insert(T o);
    void delete(T o);
    List<T> DisplayAllList();
    T displayById(int id);
    public boolean update(T o);
}
