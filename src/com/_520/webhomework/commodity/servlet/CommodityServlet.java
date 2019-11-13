package com._520.webhomework.commodity.servlet;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.domain.Commodity;

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
        // 接收请求参数
        String name = req.getParameter("name");     // 新商品名称
        String bookName = req.getParameter("bookName");     // 原来商品名称
        Double price = Double.valueOf(req.getParameter("price"));   // 商品价格
        System.out.println("name = " + name + "bookNmae = " +bookName+"price="+price);
        Commodity commodity = dao.getByName(bookName);
        if (commodity != null){
            req.getSession().setAttribute("MSG_IN_SESSION","商品已存在！");
            req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);
        }else {
            Commodity newCommodity = new Commodity();
            newCommodity.setPrice(price);
            newCommodity.setName(name);
            dao.save(newCommodity);
            req.getSession().setAttribute("MSG_IN_SESSION","保存成功！");
            req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求参数
        String name = req.getParameter("name");     // 新商品名称
        String bookName = req.getParameter("bookName");     // 原来商品名称
        Double price = Double.valueOf(req.getParameter("price"));   // 商品价格
        System.out.println("name = " + name + "bookNmae = " +bookName+"price="+price);
        Commodity commodity = dao.getByName(bookName);
        System.out.println(commodity);
        if (commodity == null){
            req.getSession().setAttribute("MSG_IN_SESSION","修改失败！");
            req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);
        }else {
            commodity.setName(name);
            commodity.setPrice(price);
            dao.update(commodity,commodity.getId());
            req.getSession().setAttribute("MSG_IN_SESSION","修改成功！");
            req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        dao.delete(dao.getByName(bookName).getId());
        req.getSession().setAttribute("MSG_IN_SESSION","删除成功！");
        req.getRequestDispatcher("/jsp/success.jsp").forward(req,resp);
    }

    private void listAll(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        String pwd = req.getParameter("pwd");
        req.getSession().setAttribute("COMMODITY_IN_SESSION",dao.listAll());

        if ("management".equals(pwd)){
            System.out.println("-------------management-----------------");
            req.getRequestDispatcher("/jsp/backstage.jsp").forward(req,resp);

        }else {
            req.getRequestDispatcher("/jsp/commodity.jsp").forward(req,resp);

        }
    }
}
