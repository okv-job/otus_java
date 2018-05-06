package ru.korytnikov.oleg.webserver.controller;

import com.google.gson.Gson;
import ru.korytnikov.oleg.Main;
import ru.korytnikov.oleg.webserver.model.StatusInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Status extends HttpServlet {

    private static Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatusInfo statusInfo = Main.getStatusInfo();
        statusInfo.setHitCount(Main.getService().getHits());
        statusInfo.setMissCount(Main.getService().getMiss());
        resp.getWriter().write(gson.toJson(statusInfo));
    }
}
