package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);
    List<Order> findUserOrder(int id);
    boolean delete(int id);
    boolean buyBasket(int id);
    List<Order> paidOrder(int id);
    List<Order> usersOrders();
    boolean confirmOrder(int id);
    boolean cancelOrder(int id);

}
