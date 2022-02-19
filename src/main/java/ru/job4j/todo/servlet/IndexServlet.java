package ru.job4j.todo.servlet;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTasks", HbnStore.instOf().findAll());
        req.setAttribute("taskDoneTrue", HbnStore.instOf().findByStatusTask(true));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.setAttribute("allCategory", HbnStore.instOf().findAllCategory());
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String[] ctgIds = req.getParameterValues("ctgId");
        User currentUser = (User) req.getSession().getAttribute("user");
        HbnStore.instOf().add(new Item(req.getParameter("description"), currentUser), ctgIds);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
