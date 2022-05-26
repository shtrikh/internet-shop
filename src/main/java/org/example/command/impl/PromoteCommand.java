package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PromoteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PromoteCommand.class);

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        userService.promote(Integer.parseInt(request.getParameter("userIdPromote")));
        LOGGER.debug("User successfully promote to admin");
        return "/users";
    }
}
