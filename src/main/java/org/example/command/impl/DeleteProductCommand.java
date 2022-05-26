package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteProductCommand.class);

    private ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        productService.deleteProduct(Integer.parseInt(request.getParameter("productIdDelete")));
        LOGGER.debug("Product successfully deleted.");
        return "admin/home.jsp";
    }
}
