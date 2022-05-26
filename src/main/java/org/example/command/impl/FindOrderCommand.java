package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getOrderService();
    private final ProductService productService = ServiceFactory.getProductService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Order> selectedOrders = orderService
                .findUserOrder(Integer.parseInt(session.getAttribute("userId").toString()));
        double priceUsdSum =0;
        double priceUahSum = 0;
        for(int i = 0; i < selectedOrders.size(); i++){
            priceUsdSum += productService.findProductById(selectedOrders.get(i).getProductId()).getPrice().doubleValue();
            priceUahSum += productService.findProductById(selectedOrders.get(i).getProductId()).getPriceUah().doubleValue();

        }
        request.setAttribute("totalPriceUsd", priceUsdSum);
        request.setAttribute("totalPriceUah", priceUahSum);
        request.setAttribute("userOrders", selectedOrders);
        LOGGER.debug("Order successfully selected");
        return "user/basket.jsp";
    }
}
