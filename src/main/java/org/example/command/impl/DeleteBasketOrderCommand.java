package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.service.OrderService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBasketOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteBasketOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderService.delete(Integer.parseInt(request.getParameter("orderIdDelete")));
        LOGGER.debug("Product successfully deleted from basket.");
        return "/basket";
    }

}
