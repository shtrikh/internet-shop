package org.example.service;

import org.example.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);

    List<Product> findAll();

    List<Product> findAll(int pageSize, int pageNumber, String sortBy, String sortOrder, String filterByValue, String filterByColumn);
    Product findProductById(int id);
    Product findLastAddedProduct();
}
