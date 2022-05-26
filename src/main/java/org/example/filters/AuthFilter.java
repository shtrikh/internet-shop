package org.example.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {


    private static final String USER_ROLE = "userRole";
    private List<String> noRegNeeded = new ArrayList<>(Arrays.asList("/previous-page","/next-page","/set-param","/index","/find-all-products", "/login", "/registration", "/logout", "/no-access"));
    private List<String> userAccessPages = new ArrayList<>(Arrays.asList("/previous-page","/next-page","/set-param", "/index","/login", "/registration", "/logout", "/find-all-products",
            "/buy", "/basket", "/order-delete", "/buy-confirm", "/my-orders", "/conf-delete", "/home", "/basket", "/my-orders", "/ban", "/no-access"));
    private List<String> adminAccessPages = new ArrayList<>(Arrays.asList("/previous-page","/next-page","/set-param","/home","/index","/login", "/registration", "/logout", "/ban", "/find-all-products",
            "/add-products", "/users", "/promote", "/usersOrders", "/confirmOrder", "/cancelOrder", "/delete-product", "/added-product",
            "/added-products", "/users", "/users-orders", "/no-access", "/edit", "/edit-product", "/find-for-update"));


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);


        String uri = request.getRequestURI();
        boolean isNoRegNeeded = false;
        for (String s : noRegNeeded) {
            if (uri.contains(s) && (!s.equals("/")) || noRegNeeded.contains(uri)) {
                isNoRegNeeded = true;
            }
        }
        boolean isUserAccessPages = false;
        for (String s : userAccessPages) {
            if (uri.contains(s) && (!s.equals("/")) || userAccessPages.contains(uri)) {
                isUserAccessPages = true;
            }
        }
        boolean isAdminAccessPages = false;
        for (String s : adminAccessPages) {
            if (uri.contains(s) && (!s.equals("/")) || adminAccessPages.contains(uri)) {
                isAdminAccessPages = true;
            }
        }
        if (!isNoRegNeeded && (session == null || session.getAttribute(USER_ROLE) == null)) {
            response.sendRedirect("/internet_shop/index");
        } else if (!isUserAccessPages && (session != null && session.getAttribute(USER_ROLE).equals("user"))) {
            response.sendRedirect("no-access.jsp");
        } else if (!isAdminAccessPages && (session != null && session.getAttribute(USER_ROLE).equals("admin"))) {
            response.sendRedirect("no-access.jsp");
        } else {
            chain.doFilter(req, resp); // Logged-in user found or no reg needed
        }

    }

    @Override
    public void destroy() {

    }
}
