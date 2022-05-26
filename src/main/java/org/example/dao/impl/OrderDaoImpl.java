package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.DaoFactory;
import org.example.dao.OrderDao;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    private final DaoFactory daoFactory;

    public OrderDaoImpl() {
        daoFactory = new DaoFactoryImpl();
    }


    @Override
    public Order save(Order model) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("INSERT INTO shop.order (userId, productId, orderStatus)" +
                                                    " VALUES (?, ?, ?)");
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getProductId());
            stmt.setString(3, model.getStatus());


            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Saved order successfully.");
                return model;
            } else {
                LOGGER.error("Failed to save order");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to save order", e);
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
    public boolean update(Order model) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("DELETE FROM shop.order WHERE orderId =?");

            stmt.setInt(1, id);


            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Delete order successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to delete order.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to delete order.", e);
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
    public List<Order> findAll() {
        List<Order> selectedOrders = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.order WHERE orderStatus='Confirmed'");
            rs = stmt.executeQuery();


            while(rs.next()) {
                selectedOrders.add(new Order.Builder().withOrderId(rs.getInt(1))
                        .withUserId(rs.getInt(2)).withProductId(rs.getInt(3))
                        .withStatus(rs.getString(4)).build());
            }

            LOGGER.debug("Orders selected successfully.");
            return selectedOrders;

        } catch (SQLException e) {
            LOGGER.error("Failed to select orders.", e);
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
    public List<Order> findUserOrder(int id) {
        List<Order> selectedOrders = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.order WHERE userId=? AND orderStatus='basket'");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            while(rs.next()) {
                selectedOrders.add(new Order.Builder().withOrderId(rs.getInt(1))
                        .withUserId(rs.getInt(2)).withProductId(rs.getInt(3))
                        .withStatus(rs.getString(4)).build());
            }

            LOGGER.debug("Orders successfully selected.");
            return selectedOrders;

        } catch (SQLException e) {
            LOGGER.error("Failed to select order.", e);
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
    public boolean buyBasket(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("UPDATE shop.order SET orderStatus ='Confirmed' WHERE userId =? AND orderStatus='basket'");

            stmt.setInt(1, id);


            if (stmt.executeUpdate() != 0) {
                LOGGER.debug("Basket successfully bought.");
                return true;
            } else {
                LOGGER.error("Failed to buy basket.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to buy basket.", e);
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
    public List<Order> paidOrder(int id) {
        List<Order> selectedOrders = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("SELECT * FROM shop.order WHERE userId=? AND orderStatus!='basket'");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            while(rs.next()) {
                selectedOrders.add(new Order.Builder().withOrderId(rs.getInt(1))
                        .withUserId(rs.getInt(2)).withProductId(rs.getInt(3))
                        .withStatus(rs.getString(4)).build());
            }

            LOGGER.debug("Orders successfully selected.");
            return selectedOrders;

        } catch (SQLException e) {
            LOGGER.error("Failed to select order.", e);
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
    public boolean cancelOrder(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("UPDATE shop.order SET orderStatus = 'Canceled' WHERE orderId =?");
            stmt.setInt(1, id);


            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Order successfully canceled with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to cancel order.");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to cancel order", e);
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
    public boolean confirmOrder(int id) {
        PreparedStatement stmt = null;

        try (Connection connection = daoFactory.getConnection()) {
            stmt = connection.prepareStatement("UPDATE shop.order SET orderStatus = 'Paid' WHERE orderId =?");
            stmt.setInt(1, id);


            if (stmt.executeUpdate() == 1) {
                LOGGER.debug("Confirm order successfully with id {}.", id);
                return true;
            } else {
                LOGGER.error("Failed to confirm order");
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to confirm order", e);
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

}
