package com._520.webhomework.commodity.servlet;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commodity")
public class CommodityServlet extends HttpServlet {
    private static ICommodityDAO dao = new CommodityDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String pwd = req.getParameter("pwd");
        if ("save".equals(pwd)) {
            this.save(req, resp);
        } else if ("delete".equals(pwd)) {
            this.delete(req, resp);
        } else if ("update".equals(pwd)) {
            this.update(req, resp);
        } else {
            try {
                this.listAll(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void listAll(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        req.getSession().setAttribute("COMMODITY_IN_SESSION",dao.listAll());
        req.getRequestDispatcher("/jsp/commodity.jsp").forward(req,resp);
    }
}
