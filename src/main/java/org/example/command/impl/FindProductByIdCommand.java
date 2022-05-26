package org.example.command.impl;

import org.example.command.Command;
import org.example.model.Product;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindProductByIdCommand implements Command {

    private final ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Product selectedProduct = productService.findProductById(Integer.parseInt(request.getParameter("updateId")));
        request.setAttribute("updateSelected", selectedProduct);
        return "admin/edit-product.jsp";
    }
}
