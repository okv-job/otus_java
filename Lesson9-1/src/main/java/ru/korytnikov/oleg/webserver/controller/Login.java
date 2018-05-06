package ru.korytnikov.oleg.webserver.controller;

import com.google.gson.Gson;
import ru.korytnikov.oleg.webserver.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends HttpServlet {

    private final String LOGIN = "admin";
    private final String PASSWORD = "admin";
    private static Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = gson.fromJson(req.getReader(), User.class);
        System.out.println(user);

        if (user.getLogin().equals(LOGIN) && user.getPassword().equals(PASSWORD)) {
            System.out.println("LOGGED IN");
            HttpSession session = req.getSession();
            session.setAttribute("isLoggedIn", true);
            sendAnswer(resp, true);
        } else {
            System.out.println("AUTH FAILED");
            sendAnswer(resp, false);
        }
    }

    private void sendAnswer(HttpServletResponse resp, boolean answer) throws IOException {
        Map<String, Boolean> response = new HashMap<>();
        response.put("result", answer);
        resp.getWriter().write(gson.toJson(response));
    }
}
