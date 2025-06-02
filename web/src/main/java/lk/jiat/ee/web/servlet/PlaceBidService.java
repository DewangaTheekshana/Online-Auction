package lk.jiat.ee.web.servlet;

import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.RemoteBidSave;

import java.io.*;

@WebServlet("/PlaceBidServlet")
public class PlaceBidService extends HttpServlet {

    private final Gson gson = new Gson();

    @EJB
    private RemoteBidSave remoteBidSave; // Inject your Singleton EJB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bid = req.getParameter("bidAmount");
        String productId = req.getParameter("productId");

        System.out.println(bid);
        System.out.println(productId);

        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        remoteBidSave.placeBid(Integer.parseInt(productId),Double.parseDouble(bid),user.getId());

        resp.getWriter().write("success");

    }
}