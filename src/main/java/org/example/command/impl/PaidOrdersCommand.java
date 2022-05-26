package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaidOrdersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PaidOrdersCommand.class);

    private final OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Order> selectedOrders = orderService
                .paidOrder(Integer.parseInt(session.getAttribute("userId").toString()));
        request.setAttribute("userConfirmedOrders", selectedOrders);
        return "user/my-orders.jsp";
    }
}
