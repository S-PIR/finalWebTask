package by.epamlab.repositories;

import by.epamlab.model.beans.Product;

import java.util.List;

public interface CrudRepository<T, ID> {
    T add(T t);

    void delete(T t);

    T update (T t);

    T findOne(ID id);

    List<T> findAll();

}
