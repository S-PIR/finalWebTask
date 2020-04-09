package by.epamlab.service;

import by.epamlab.dto.ProductDto;
import by.epamlab.model.beans.Category;
import by.epamlab.model.beans.Product;
import by.epamlab.validation.product.ProductExistsException;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto) throws ProductExistsException;
    Product updateProduct(ProductDto productDto, int productId) throws ProductExistsException;
    Product findProduct(Integer productId);
    void deleteProduct(Integer productId);
    List<Product> findProductsByCategory(String category);
}