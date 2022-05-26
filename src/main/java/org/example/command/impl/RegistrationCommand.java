package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.enums.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;
import org.example.validator.ProductValidator;
import org.example.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private final UserValidator userValidator = new UserValidator();
    private final UserService userService = ServiceFactory.getUserService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User newUser = new User.Builder()
                .withLogin(request.getParameter("login"))
                .withPassword(request.getParameter("password"))
                .withName(request.getParameter("name"))
                .withSurname(request.getParameter("surname"))
                .withRole(Role.USER)
                .withEmail(request.getParameter("email"))
                .build();
        userValidator.validateCreateUser(newUser);
        newUser = userService.saveUser(newUser);
        HttpSession session = request.getSession();
        session.setAttribute("status", "unbanned");
        session.setAttribute("userId", newUser.getId());
        session.setAttribute("userRole", "user");
        LOGGER.info("New user registered with id {}.", newUser.getId());
        return "redirect:user/home.jsp";

    }


}
