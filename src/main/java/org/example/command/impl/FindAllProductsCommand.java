package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.Product;
import org.example.service.ProductService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllProductsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindAllProductsCommand.class);

    private final ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int pageSize = Integer.parseInt(session.getAttribute("pageSize").toString());
        int pageNumber = Integer.parseInt(session.getAttribute("pageNumberSession").toString());
        String sortBy = session.getAttribute("sortBy").toString();
        String sortOrder = session.getAttribute("sortOrder").toString();
        String filterByValue = session.getAttribute("filterByValue").toString();
        String filterByColumn = session.getAttribute("filterByColumn").toString();
        List<Product> products = productService.findAll(pageSize, pageNumber, sortBy, sortOrder, filterByValue, filterByColumn);
        LOGGER.debug("Products successfully selected.");
        if(products.size()/pageSize == 0){
            session.setAttribute("numberOfPages", 0);
        }else{
            session.setAttribute("numberOfPages", 1);
        }
        request.setAttribute("allProducts", products);

        if(session.getAttribute("userRole") != null) {
            return session.getAttribute("userRole") + "/home.jsp";
        }else{
            return "index.jsp";
        }
    }
}