package org.example.dao;

import org.example.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> findAll(int pageSize, int pageNumber, String sortBy, String sortOrder, String filterByValue, String filterByColumn);
    Product findProductById(int id);
    Product findLastAddedProduct();
}
