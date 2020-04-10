package by.epamlab.repositories;

import by.epamlab.exception.QuantityOutOfRangeException;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.cart.Saleable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product getProductByName(String name);
    List<Product> findAllByCategory(String category);
    List<Product> findAllByPage(int limit, int offset);
    List<Product> findAllByCategoryAndPage(String category, int limit, int offset);
    int updateAll(Map<Saleable, Integer> products) throws QuantityOutOfRangeException;
    long count();
}
