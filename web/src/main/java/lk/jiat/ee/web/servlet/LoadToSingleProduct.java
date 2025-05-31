package lk.jiat.ee.web.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.model.Product;
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

        JsonObject jsonObject = new JsonObject();

        jsonObject.add("product", gson.toJsonTree(products));

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));

    }
}
