package org.example.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.Command;
import org.example.command.impl.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetStartPageServlet", urlPatterns = "/")
public class ShopServlet extends HttpServlet{


    private static final Logger LOGGER = LogManager.getLogger(ShopServlet.class);

    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put("/find-all-products", new FindAllProductsCommand());
        commands.put("/login", new LoginCommand());
        commands.put("/registration", new RegistrationCommand());
        commands.put("/add-products", new CreateProductCommand());
        commands.put("/users", new FindAllUsersCommand());
        commands.put("/ban", new BanCommand());
        commands.put("/promote", new PromoteCommand());
        commands.put("/deleteUser", new DeleteUserCommand());
        commands.put("/buy", new BuyCommand());
        commands.put("/basket", new FindOrderCommand());
        commands.put("/order-delete", new DeleteBasketOrderCommand());
        commands.put("/buy-confirm", new BuyBasketCommand());
        commands.put("/my-orders", new PaidOrdersCommand());
        commands.put("/conf-delete", new DeleteConfirmedOrderCommand());
        commands.put("/usersOrders", new FindUsersOrdersCommand());
        commands.put("/confirmOrder", new ConfirmOrderCommand());
        commands.put("/cancelOrder", new CancelOrderCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/delete-product", new DeleteProductCommand());
        commands.put("/added-product", new FindAddedProductCommand());
        commands.put("/index", new IndexCommand());
        commands.put("/banana", new MoveToBanCommand());
        commands.put("/edit", new ProductUpdateCommand());
        commands.put("/find-for-update", new FindProductByIdCommand());
        commands.put("/set-param", new SetFindParamCommand());
        commands.put("/next-page", new NextPageCommand());
        commands.put("/previous-page", new PreviousPageCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI().replace("/internet_shop", "");
        LOGGER.debug(path);
        Command command = commands.getOrDefault(path, (req, resp)->"/index.jsp");

        String page = command.execute(request, response);
        LOGGER.debug(page);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }
}
