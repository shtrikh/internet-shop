package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.enums.Category;
import org.example.enums.Size;
import org.example.model.Product;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;
import org.example.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class CreateProductCommand implements Command {

    private final ProductService productService = ServiceFactory.getProductService();
    private final ProductValidator productValidator = new ProductValidator();
    private static final Logger LOGGER = LogManager.getLogger(CreateProductCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        productValidator.validateCategory(request.getParameter("productCategory"));
        productValidator.validateSize(request.getParameter("productSize"));
        productValidator.validatePrice(request.getParameter("priceUsd"));
        productValidator.validatePriceUah(request.getParameter("priceUah"));

        Product newProduct = new Product.Builder()
                .withName(request.getParameter("productName"))
                .withColor(request.getParameter("productColor").toLowerCase())
                .withCategory(Category.valueOf(request.getParameter("productCategory").toUpperCase()))
                .withSize(Size.valueOf(request.getParameter("productSize").toUpperCase()))
                .withPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("priceUsd"))))
                .withPriceUah(BigDecimal.valueOf(Double.parseDouble(request.getParameter("priceUah"))))
                .build();
        productService.createProduct(newProduct);
        LOGGER.debug("Product successfully created.");
        return "/added-product";
    }
}
