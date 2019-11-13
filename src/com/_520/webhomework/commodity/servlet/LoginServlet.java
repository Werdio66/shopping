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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static IUserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 接收请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.getByUsername(username);
        if (user == null){
            req.setAttribute("MSG","账号不存在，请注册！");
            req.getRequestDispatcher("/jsp/user/register.jsp").forward(req,resp);
        }else {
            if (user.getPassword().equals(password)){// 登录成功
                resp.sendRedirect("/commodity?pwd=management");
            }else {
                req.setAttribute("MSG","密码不正确，请重新输入！");
                req.getRequestDispatcher("/jsp/user/login.jsp").forward(req,resp);
            }
        }
    }
}
