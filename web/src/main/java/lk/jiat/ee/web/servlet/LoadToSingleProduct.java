package lk.jiat.ee.web.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.model.AutoBid;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.DataStorage;

import java.io.IOException;
import java.util.List;

@WebServlet("/LoadToSingleProduct")
public class LoadToSingleProduct extends HttpServlet {

    @EJB
    private DataStorage dataStorage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();

        String productId = request.getParameter("id");

        Product products = dataStorage.getProductById(Integer.valueOf(productId));

        System.out.println(gson.toJson(products));

        User user = (User) request.getSession().getAttribute("user");

        boolean userHasAutoBid = false;
        double autoBidAmount = 0;
        List<AutoBid> autoBid = dataStorage.getAutoBiddersForProduct(Integer.parseInt(productId));

        for (AutoBid autoBid1 : autoBid) {
            if (autoBid1.getUserId() == (user.getId())) {
                userHasAutoBid = true;
                autoBidAmount = autoBid1.getMaxBid();
                break;
            }
        }

        System.out.println("autoBid List Single Product"+autoBid);

        JsonObject jsonObject = new JsonObject();

        jsonObject.add("product", gson.toJsonTree(products));
        jsonObject.addProperty("autoBidEnabled", userHasAutoBid);
        jsonObject.addProperty("autoBidAmount", autoBidAmount);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));

    }
}
