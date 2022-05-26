package org.example.service.impl;

import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.UserService;

public class ServiceFactory {

    private ServiceFactory() {
        //private constructor
    }

    public static ProductService getProductService(){
        return ProductServiceImpl.getInstance();
    }
    public static UserService getUserService(){
        return UserServiceImpl.getInstance();
    }
    public static OrderService getOrderService(){
        return OrderServiceImpl.getInstance();
    }
}
