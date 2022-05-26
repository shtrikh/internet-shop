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

public class SetFindParamCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SetFindParamCommand.class);
    private final ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String sortBy = request.getParameter("sortBy");
        String sortOrder = request.getParameter("sortOrder");
        String filterByValue = request.getParameter("filterByValue");
        String filterByColumn = request.getParameter("filterByColumn");

        HttpSession session = request.getSession();
        session.setAttribute("pageSize", pageSize);
        session.setAttribute("pageNumberSession", pageNumber);
        session.setAttribute("sortBy", sortBy);
        session.setAttribute("sortOrder",sortOrder);
        session.setAttribute("filterByValue",filterByValue);
        session.setAttribute("filterByColumn", filterByColumn);

        List<Product> products = productService.findAll(pageSize, pageNumber, sortBy, sortOrder, filterByValue, filterByColumn);
        int numberOfPages = products.size()/pageSize;
        session.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("allProducts", products);
        LOGGER.debug("Param for search set successfully.");
        if(session.getAttribute("userRole") != null) {
            return session.getAttribute("userRole") + "/home.jsp";
        }else{
            return "index.jsp";
        }
    }
}
