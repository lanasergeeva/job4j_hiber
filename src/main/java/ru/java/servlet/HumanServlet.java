package ru.java.servlet;

import ru.java.category.City;
import ru.java.category.Human;
import ru.java.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HumanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = HbmStore.instanceOf().allCities();
        req.setAttribute("allCities", cities);
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String[] cIds = req.getParameterValues("cIds");
        String info = req.getParameter("info");
        Human human = Human.of(info);
        HbmStore.instanceOf().addNewHuman(human, cIds);
        resp.sendRedirect(req.getContextPath() + "/humans");
    }
}
