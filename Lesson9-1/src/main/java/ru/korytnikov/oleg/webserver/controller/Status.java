package ru.korytnikov.oleg.webserver.controller;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import ru.korytnikov.oleg.dao.hibernate.DBServiceHybernateImpl;
import ru.korytnikov.oleg.webserver.model.CacheConfig;
import ru.korytnikov.oleg.webserver.model.StatusInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Status extends HttpServlet {

    private static Gson gson = new Gson();

    private static CacheConfig cacheConfig;
    private static DBServiceHybernateImpl serviceHybernate;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = (ApplicationContext) getServletContext().getAttribute("AppCtx");
        serviceHybernate = (DBServiceHybernateImpl) getServletContext().getAttribute("hibernateService");
        cacheConfig = serviceHybernate.getCacheConfig();
        System.out.println("END OF INIT");
//        cacheConfig = (CacheConfig) ctx.getBean("statusInfo");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatusInfo statusInfo = new StatusInfo(serviceHybernate.getHits(), serviceHybernate.getMiss(), cacheConfig);
        System.out.println(statusInfo);
        resp.getWriter().write(gson.toJson(statusInfo));
    }
}
