package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.User;
import org.example.service.ProductService;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN_ERROR = "loginError";

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User selectedUser = userService.findUser(login);
        HttpSession session = request.getSession();

        boolean banned = selectedUser.getBan();
        if (login != null && !login.equals("") && password != null && !password.equals("")) {
            if (!banned) {
                if (password.equals(selectedUser.getPassword())) {
                    String role = selectedUser.getRole().toString().toLowerCase();
                    session.setAttribute("userId", selectedUser.getId());
                    session.setAttribute("userRole", role);
                    LOGGER.info("User login with id {}.", selectedUser.getId());
                    return "redirect:" + role + "/home.jsp";
                } else {
                    request.setAttribute(LOGIN_ERROR, "Wrong password");
                    return "login.jsp";
                }
            } else {
                request.setAttribute(LOGIN_ERROR, "You are banned =(");
                return "login.jsp";
            }
        } else {
            request.setAttribute(LOGIN_ERROR, "Incorrect input");
            return "login.jsp";
        }
    }
}
