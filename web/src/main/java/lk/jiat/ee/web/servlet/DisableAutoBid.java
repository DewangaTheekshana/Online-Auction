package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;

import java.io.IOException;

@WebServlet("/disableautobid")
public class DisableAutoBid extends HttpServlet {

    @EJB
    DataStorage dataStorage;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getParameter("productId");
        User user = (User) req.getSession().getAttribute("user");

        dataStorage.removeAutoBid(Integer.parseInt(productId) ,user.getId());

        resp.getWriter().write("Deactivate Auto Bid Successfully");

    }
}
