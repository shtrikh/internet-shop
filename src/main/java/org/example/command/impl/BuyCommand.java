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

public class BuyCommand implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();

    private static final Logger LOGGER = LogManager.getLogger(BuyCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order newOrder = new Order.Builder()
                .withUserId(Integer.parseInt(session.getAttribute("userId").toString()))
                .withProductId(Integer.parseInt(request.getParameter("productId")))
                .withStatus("basket")
                .build();
        orderService.save(newOrder);
        LOGGER.debug("Product successfully added to basket");
        return "/find-all-products";
    }
}
