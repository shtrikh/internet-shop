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

public class ProductUpdateCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ProductUpdateCommand.class);
    private final ProductService productService = ServiceFactory.getProductService();
    private final ProductValidator productValidator = new ProductValidator();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("updateId"));
        String name = null;
        String color = null;
        Category category = null;
        Size size = null;
        BigDecimal price = null;
        BigDecimal priceUah = null;
        if(!request.getParameter("updateName").isEmpty()){
            name = request.getParameter("updateName");
        }
        if(!request.getParameter(("updateColor")).isEmpty()) {
            color = request.getParameter(("updateColor"));
        }
        if(!request.getParameter("updateCategory").isEmpty()){
            productValidator.validateCategory(request.getParameter("updateCategory"));
            category = Category.valueOf(request.getParameter("updateCategory"));
        }
        if(!request.getParameter("updateSize").isEmpty()){
            productValidator.validateSize(request.getParameter("updateSize"));
            size = Size.valueOf(request.getParameter("updateSize"));
        }
        if(Double.parseDouble(request.getParameter("updatePrice")) != 0){
            productValidator.validatePrice(request.getParameter("updatePrice"));
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("updatePrice")));
        }
        if(Double.parseDouble(request.getParameter("updatePriceUah")) != 0){
            productValidator.validatePriceUah(request.getParameter("updatePriceUah"));
            priceUah = BigDecimal.valueOf(Double.parseDouble(request.getParameter("updatePriceUah")));
        }

        Product newProduct = new Product.Builder()
                .withId(id)
                .withName(name)
                .withColor(color)
                .withCategory(category)
                .withSize(size)
                .withPrice(price)
                .withPriceUah(priceUah)
                .build();
        productService.updateProduct(newProduct);
        LOGGER.debug("Product successfully edited.");
        return "admin/home.jsp";
    }
}
