package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.Product;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAddedProductCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindAddedProductCommand.class);

    private final ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Product addedProduct = productService.findLastAddedProduct();
        request.setAttribute("addedProduct", addedProduct);
        LOGGER.debug("Last added product successfully selected.");
        return "admin/added-product.jsp";
    }
}
