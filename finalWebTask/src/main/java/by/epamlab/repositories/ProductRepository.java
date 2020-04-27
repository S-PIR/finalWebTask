package by.epamlab.repositories;

import by.epamlab.exception.QuantityOutOfRangeException;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.cart.Saleable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product getProductByName(String name);
    List<Product> findAllByCategory(String category);
    List<Product> findAllByPageAndOrder(int limit, int offset, String order);
//    List<Product> findAllByCategoryAndPage(String category, int limit, int offset);
    List<Product> findAllByCategoryAndPageAndOrder(String category, int limit, int offset, String order);
    List<Product> findAllByCriterion(String criterion);
    List<Product> findAllByCategoryAndCriterion(String category, String criterion);
    int updateAll(Map<Saleable, Integer> products) throws QuantityOutOfRangeException;
    long count(String category);
}
