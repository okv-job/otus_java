package ru.korytnikov.oleg.webserver.controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class State extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("isLoggedIn") != null) {
            boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
            if (!isLoggedIn) {
                sendAnswer(resp, false);
            } else {
                sendAnswer(resp, true);
            }
        } else {
            sendAnswer(resp, false);
        }
    }

    private void sendAnswer(HttpServletResponse response, boolean answer) throws IOException {
        Gson gson = new Gson();
        Map<String, Boolean> result = new HashMap<>();
        result.put("result", answer);
        response.getWriter().write(gson.toJson(result));
    }
}
