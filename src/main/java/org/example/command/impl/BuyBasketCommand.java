package org.example.command.impl;

import com.mysql.cj.log.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.service.OrderService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyBasketCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(BuyBasketCommand.class);

    private final OrderService orderService = ServiceFactory.getOrderService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        orderService.buyBasket(Integer.parseInt(session.getAttribute("userId").toString()));
        LOGGER.debug("Basket successfully bought.");
        return "/my-orders";
    }
}
