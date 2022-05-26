package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.DaoFactory;
import org.example.dao.ProductDao;
import org.example.enums.Category;
import org.example.enums.Size;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);

    private final DaoFactory daoFactory;

    public ProductDaoImpl() {
        daoFactory = new DaoFactoryImpl();
    }


    @Override
    public Product save(Product model) {
        PreparedStatement stmt = null;
        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("INSERT INTO shop.product(name, color, category, size, price, priceUa)" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getColor().toLowerCase());
            stmt.setString(3, model.getCategory().toString().toUpperCase());
            stmt.setString(4, model.getSize().toString().toUpperCase());
            stmt.setBigDecimal(5, model.getPrice());
            stmt.setBigDecimal(6, model.getPriceUah());
            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Saved product successfully.");

                return model;
            } else {
                LOGGER.error("Failed to save product.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to save product.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public boolean update(Product product) {
        PreparedStatement stmt = null;
        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement(
                    "UPDATE shop.product SET name = ?, color = ?," +
                            " category = ?, size = ?, price = ?, priceUa = ? WHERE id = ?");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getColor());
            stmt.setString(3, product.getCategory().toString());
            stmt.setString(4, product.getSize().toString());
            stmt.setBigDecimal(5, product.getPrice());
            stmt.setBigDecimal(6, product.getPriceUah());
            stmt.setInt(7, product.getId());

            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Deleted product successfully with id {}.", product.getId());
                return true;
            } else {
                LOGGER.error("Failed to delete product.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to delete product.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }



    @Override
    public boolean delete(int id) {
        PreparedStatement stmt = null;
        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("DELETE FROM shop.product WHERE id = ?");
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Deleted product successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to deleted product.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to delete product.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findAll(int pageSize, int pageNumber, String sortBy, String sortOrder, String filterByValue, String filterByColumn) {
        String query = getFindAllQuery(sortBy, sortOrder, filterByValue, filterByColumn);
        List<Product> selectedProduct = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement(query);
            int index = 1;
            if(!filterByValue.isEmpty() && !filterByColumn.isEmpty()) {
                stmt.setString(index++, filterByValue);
            }
                stmt.setInt(index++, pageSize * (pageNumber - 1));
                stmt.setInt(index, pageSize);

            rs = stmt.executeQuery();

            while (rs.next()) {
                selectedProduct.add(new Product.Builder()
                        .withId(rs.getInt(1))
                        .withName(rs.getString(2))
                        .withColor(rs.getString(3))
                        .withCategory(Category.valueOf(rs.getString(4).toUpperCase()))
                        .withSize(Size.valueOf(rs.getString(5).toUpperCase()))
                        .withPrice(rs.getBigDecimal(6))
                        .withPriceUah(rs.getBigDecimal(7))
                        .withAddedTime(rs.getTimestamp(8).toLocalDateTime())
                        .build());
            }

            LOGGER.debug("Product successfully selected.");
            return selectedProduct;

        } catch (SQLException e) {
            LOGGER.error("Failed to select products.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    @Override
    public Product findProductById(int id) {
        Product selectedProduct = new Product();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement(
                    "SELECT * FROM shop.product WHERE id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                selectedProduct = new Product.Builder()
                        .withId(rs.getInt(1))
                        .withName(rs.getString(2))
                        .withColor(rs.getString(3))
                        .withCategory(Category.valueOf(rs.getString(4).toUpperCase()))
                        .withSize(Size.valueOf(rs.getString(5).toUpperCase()))
                        .withPrice(rs.getBigDecimal(6))
                        .withPriceUah(rs.getBigDecimal(7))
                        .withAddedTime(rs.getTimestamp(8).toLocalDateTime())
                        .build();
                LOGGER.debug("Find product successfully with id {}.", id);
                return selectedProduct;
            } else {
                LOGGER.error("Failed to find product.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find product.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    @Override
    public Product findLastAddedProduct() {
        Product selectedProduct = new Product();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement(
                    "SELECT * FROM shop.product order by id DESC LIMIT 0,1;");
            rs = stmt.executeQuery();
            if (rs.next()) {
                selectedProduct = new Product.Builder()
                        .withId(rs.getInt(1))
                        .withName(rs.getString(2))
                        .withColor(rs.getString(3))
                        .withCategory(Category.valueOf(rs.getString(4).toUpperCase()))
                        .withSize(Size.valueOf(rs.getString(5).toUpperCase()))
                        .withPrice(rs.getBigDecimal(6))
                        .withPriceUah(rs.getBigDecimal(7))
                        .withAddedTime(rs.getTimestamp(8).toLocalDateTime())
                        .build();
                LOGGER.debug("Find product successfully.");
                return selectedProduct;
            } else {
                LOGGER.error("Failed to find product.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find product.", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing statement.", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while closing result set.", e);
            }
        }
    }

    private String getFindAllQuery(String sortBy, String sortOrder, String filterByValue, String filterByColumn) {
        String query = "SELECT * FROM shop.product ";
        if(!filterByValue.isEmpty() && !filterByColumn.isEmpty()){
            if(filterByColumn.equals("category")){
                query = query + "WHERE category = ? ";
            } else if (filterByColumn.equals("color")){
                query = query + "WHERE color = ? ";
            } else if (filterByColumn.equals("size")){
                query = query + "WHERE size = ? ";
            }
        }
        if(!sortBy.isEmpty() && !sortOrder.isEmpty()){
            if(sortBy.equals("name")){
                query = query + "ORDER BY name " + sortOrder + " ";
            } else if(sortBy.equals("price")){
                query = query + "ORDER BY price " + sortOrder + " ";
            } else{
                query = query + "ORDER BY addedTime DESC ";
            }
        } else {
            query = query + "ORDER BY addedTime DESC ";
        }
        query = query + "LIMIT ?,?";
        LOGGER.debug("Query successfully created.");
        return query;
    }



}
