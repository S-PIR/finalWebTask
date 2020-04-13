package by.epamlab.model.dao;

import by.epamlab.model.beans.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts(String category);

}
