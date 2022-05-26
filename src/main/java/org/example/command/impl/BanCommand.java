package org.example.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.service.UserService;
import org.example.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BanCommand implements Command {


    private static final Logger LOGGER = LogManager.getLogger(BanCommand.class);

    private final UserService userService = ServiceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        userService.ban(Integer.parseInt(request.getParameter("userId")), request.getParameter("banned"));
        session.setAttribute("status", "banned");
        LOGGER.debug("User banned successfully.");
        return "/users";
    }
}
