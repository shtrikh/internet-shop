package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindAllUsersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindAllUsersCommand.class);

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userService.findAll();
        request.setAttribute("allUsers", users);
        LOGGER.debug("Users successfully selected.");
        return "admin/users.jsp";
    }
}
