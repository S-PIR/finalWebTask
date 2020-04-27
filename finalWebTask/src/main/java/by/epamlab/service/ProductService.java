package by.epamlab.service;

import by.epamlab.dto.ProductDto;
import by.epamlab.model.beans.Product;
import by.epamlab.validation.product.ProductExistsException;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto) throws ProductExistsException;
    Product updateProduct(ProductDto productDto, int productId) throws ProductExistsException;
    Product findProduct(Integer productId);
    void deleteProduct(Integer productId);
    List<Product> findProductsByCategory(String category);
//    List<Product> findProductsByCategoryAndPage(String category, int limit, int offset);
    List<Product> findProductsByCategoryAndPageAndOrder(String category, int limit, int offset, String order);
    List<Product> findProductsByCategoryAndCriterion(String category, String criterion);
    long getTotalProductsNumber(String category);
}
