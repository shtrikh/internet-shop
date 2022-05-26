package org.example.command.impl;

import org.example.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PreviousPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int pageNumber = Integer.parseInt(session.getAttribute("pageNumberSession").toString());
        if(pageNumber > 1){
            pageNumber--;
            session.setAttribute("pageNumberSession", pageNumber);
        }
        return "/find-all-products";
    }
}
