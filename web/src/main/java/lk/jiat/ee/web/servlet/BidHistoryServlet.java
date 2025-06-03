package lk.jiat.ee.web.servlet;

import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.jiat.ee.core.model.Bid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/bid-history")
public class BidHistoryServlet extends HttpServlet {

    @EJB
    private DataStorage dataStorage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = dataStorage.getProductById(productId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (product != null) {
            List<Map<String, Object>> bidHistoryList = new ArrayList<>();

            for (Bid bid : product.getBidPlacedEvent()) {
                User user = dataStorage.getUserById(bid.getUserId());
                String userName = user != null ? user.getName() : "Unknown";

                Map<String, Object> entry = new HashMap<>();
                entry.put("userName", userName);
                entry.put("bidAmount", bid.getBidAmount());
                entry.put("timestamp", bid.getTimestamp()); // assuming timestamp was added to Bid

                bidHistoryList.add(entry);
            }

            String json = new Gson().toJson(bidHistoryList);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("[]");
        }
    }
}
