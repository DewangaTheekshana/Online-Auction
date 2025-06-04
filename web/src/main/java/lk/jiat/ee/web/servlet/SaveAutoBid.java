package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.model.AutoBid;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;

import java.io.IOException;
import java.util.Date;

@WebServlet("/placeautobid")
public class SaveAutoBid extends HttpServlet {

    @EJB
    DataStorage dataStorage;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        String productId = req.getParameter("productId");
        User user = (User) req.getSession().getAttribute("user");

        AutoBid autoBid = new AutoBid();
        autoBid.setUserId(user.getId());
        autoBid.setProductId(Integer.parseInt(productId));
        autoBid.setMaxBid(Integer.parseInt(bid));
        autoBid.setRegisteredAt(new Date());

        dataStorage.registerAutoBid(autoBid);

        resp.getWriter().write("Activate Auto Bid Successfully");

    }
}
