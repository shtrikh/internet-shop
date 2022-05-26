package org.example.dao;

import org.example.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{
    List<Order> findUserOrder (int id);
    boolean buyBasket(int id);
    List<Order> paidOrder(int id);

    boolean cancelOrder(int id1);

    boolean confirmOrder(int id1);
}
