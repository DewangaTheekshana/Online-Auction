package lk.jiat.ee.web.servlet;

import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.ejb.remote.DataStorage;

import java.io.IOException;

@WebServlet("/bid-history")
public class BidHistoryServlet extends HttpServlet {

    @EJB
    private DataStorage dataStorage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = dataStorage.getProductById(productId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (product != null) {
            String json = new Gson().toJson(product.getBidPlacedEvent());
            response.getWriter().write(json);
        } else {
            response.getWriter().write("[]");
        }
    }
}
