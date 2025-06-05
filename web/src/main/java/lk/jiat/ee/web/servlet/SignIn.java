package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.ejb.remote.RemoteLogin;

import java.io.IOException;
import java.util.List;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet {

    @EJB
    RemoteLogin remoteLogin;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        boolean loginStatus = remoteLogin.validate(email, password);

        System.out.println("Login Status Boolean "+loginStatus);


        if (loginStatus) {

            User user = remoteLogin.findUser(email);

            System.out.println("Session Store Login Name : "+user.getName());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            System.out.println(session.getAttribute("user"));

            response.getWriter().write("success");
        } else {
            response.getWriter().write("Invalid email or password.");
        }

    }
}