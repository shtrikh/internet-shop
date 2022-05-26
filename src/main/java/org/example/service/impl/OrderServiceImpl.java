package org.example.service.impl;

import org.example.dao.OrderDao;
import org.example.dao.impl.OrderDaoImpl;
import org.example.model.Order;
import org.example.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderService INSTANCE = new OrderServiceImpl();

    private final OrderDao orderDao = new OrderDaoImpl();

    private OrderServiceImpl() {
        //private constructor
    }


    protected static OrderService getInstance() {
        return INSTANCE;
    }

    @Override
    public Order save(Order order) {
        return orderDao.save(order);
    }

    @Override
    public List<Order> findUserOrder(int id) {
        return orderDao.findUserOrder(id);
    }

    @Override
    public boolean delete(int id) {
        return orderDao.delete(id);
    }

    @Override
    public boolean buyBasket(int id) {
        return orderDao.buyBasket(id);
    }

    @Override
    public List<Order> paidOrder(int id) {
        return orderDao.paidOrder(id);
    }

    @Override
    public List<Order> usersOrders() {
        return orderDao.findAll();
    }

    @Override
    public boolean confirmOrder(int id) {
        return orderDao.confirmOrder(id);
    }

    @Override
    public boolean cancelOrder(int id) {
        return orderDao.cancelOrder(id);
    }
}
