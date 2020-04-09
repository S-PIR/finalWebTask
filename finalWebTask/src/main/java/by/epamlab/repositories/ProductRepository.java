package by.epamlab.repositories;

import by.epamlab.exception.QuantityOutOfRangeException;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.cart.Saleable;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product getProductByName(String name);
    List<Product> findAllByCategory(String category);
    int updateAll(Map<Saleable, Integer> products) throws QuantityOutOfRangeException;
}
