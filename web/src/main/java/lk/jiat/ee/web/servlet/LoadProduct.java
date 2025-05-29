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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/LoadProduct")
public class LoadProduct extends HttpServlet {

    @EJB
    private DataStorage dataStorage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = dataStorage.getProducts();

        System.out.println(products);

        Gson gson = new Gson();

        for (Product product : products) {
                    product.getId();
                    product.getName();
                    product.getImage();
                    product.getBasePrice();
                    product.getEndTime();
        }

        JsonObject jsonObject = new JsonObject();

        jsonObject.add("productList", gson.toJsonTree(products));

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));

    }

}
