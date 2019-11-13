package com._520.webhomework.commodity.servlet;

import com._520.webhomework.commodity.dao.IUserDAO;
import com._520.webhomework.commodity.dao.impl.UserDAOImpl;
import com._520.webhomework.commodity.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static IUserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDAO.add(user);

        req.getSession().setAttribute("MSG_IN_SESSION", "注册成功！");
        req.getRequestDispatcher("/jsp/user/success.jsp").forward(req,resp);
    }
}
