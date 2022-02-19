package ru.job4j.todo.servlet;

import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (HbnStore.instOf().findUserByEmail(email) != null) {
            req.setAttribute("error", "Пользователь с таким email уже зарегистрирован на сайте");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            HbnStore.instOf().addUser(
                    new User(
                            name,
                            email,
                            password
                    )
            );
        }
        req.getRequestDispatcher("auth.jsp").forward(req, resp);
    }
}
