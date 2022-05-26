package org.example.service.impl;

import org.example.dao.ProductDao;
import org.example.dao.impl.ProductDaoImpl;
import org.example.model.Product;
import org.example.service.ProductService;

import java.util.List;

public final class ProductServiceImpl implements ProductService {

    private static final ProductService INSTANCE = new ProductServiceImpl();

    private final ProductDao productDao = new ProductDaoImpl();

    private ProductServiceImpl() {
        //private constructor
    }


    protected static ProductService getInstance() {
        return INSTANCE;
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        Product oldProduct = productDao.findProductById(product.getId());
        setUpdatedFields(oldProduct, product);
        return productDao.update(oldProduct);
    }

    private void setUpdatedFields(Product oldProduct, Product newProduct){
        if(newProduct.getName() != null){
            oldProduct.setName(newProduct.getName());
        }
        if(newProduct.getColor() != null){
            oldProduct.setColor(newProduct.getColor());
        }
        if(newProduct.getCategory() != null){
            oldProduct.setCategory(newProduct.getCategory());
        }
        if(newProduct.getSize() != null){
            oldProduct.setSize(newProduct.getSize());
        }
        if(newProduct.getPrice() != null){
            oldProduct.setPrice(newProduct.getPrice());
        }
        if(newProduct.getPriceUah() != null){
            oldProduct.setPriceUah(newProduct.getPriceUah());
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        return productDao.delete(id);
    }


    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
    @Override
    public List<Product> findAll(int pageSize, int pageNumber, String sortBy, String sortOrder, String filterByValue, String filterByColumn) {
        return productDao.findAll(pageSize, pageNumber, sortBy, sortOrder, filterByValue, filterByColumn);
    }

    @Override
    public Product findProductById(int id) {
        return productDao.findProductById(id);
    }

    @Override
    public Product findLastAddedProduct() {
        return productDao.findLastAddedProduct();
    }
}
