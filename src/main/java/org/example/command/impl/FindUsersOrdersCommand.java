package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindUsersOrdersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindUsersOrdersCommand.class);
    private final OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Order> selectedOrders = orderService.usersOrders();
        request.setAttribute("allUsersOrders", selectedOrders);
        LOGGER.debug("Orders successfully selected.");
        return "admin/users-orders.jsp";
    }
}
