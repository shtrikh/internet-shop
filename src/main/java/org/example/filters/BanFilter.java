//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebFilter(filterName = "BanFilter", urlPatterns = "/*")
public class BanFilter implements Filter {
    private final UserService userService = ServiceFactory.getUserService();
    private static final Logger LOGGER = LogManager.getLogger(BanFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        List<String> banEnable = new ArrayList<>(Arrays.asList("/buy", "/basket", "/order-delete",
                "/buy-confirm", "/my-orders", "/conf-delete", "/home", "/basket", "/my-orders"));
        String uri = req.getRequestURI();

        boolean banned = false;
        for (String s : banEnable) {
            if (uri.contains(s) && (!s.equals("/")) || banEnable.contains(uri)) {
                banned = true;
            }
        }

        if (banned) {
            User selectedUser = userService.findUser((Integer) session.getAttribute("userId"));
            if (selectedUser.getBan()) {
                resp.sendRedirect("/internet_shop/banana");
            }
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
