package com._520.webhomework.commodity.servlet;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.query.CommodityQuery;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/commodity")
public class CommodityServlet extends HttpServlet {
    private static ICommodityDAO dao = new CommodityDAOImpl();
    private CommodityQuery commodityQuery = new CommodityQuery();

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
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/success.jsp").forward(req,resp);
        }else {
            Commodity newCommodity = new Commodity();
            newCommodity.setPrice(price);
            newCommodity.setName(name);
            dao.save(newCommodity);
            req.getSession().setAttribute("MSG_IN_SESSION","保存成功！");
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/success.jsp").forward(req,resp);
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
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/success.jsp").forward(req,resp);
        }else {
            commodity.setName(name);
            commodity.setPrice(price);
            dao.update(commodity,commodity.getId());
            req.getSession().setAttribute("MSG_IN_SESSION","修改成功！");
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/success.jsp").forward(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        dao.delete(dao.getByName(bookName).getId());
        req.getSession().setAttribute("MSG_IN_SESSION","删除成功！");
        req.getRequestDispatcher("/WEB-INF/jsp/commodity/success.jsp").forward(req,resp);
    }

    private void listAll(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        String pwd = req.getParameter("pwd");

        commodityQuery = new CommodityQuery();
        this.setCommodityQuery(req,resp);
        req.setAttribute("commodityQuery",commodityQuery);
        req.getSession().setAttribute("COMMODITY_IN_SESSION",dao.query(commodityQuery));

        if ("management".equals(pwd)){
            System.out.println("-------------management-----------------");
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/backstage.jsp").forward(req,resp);

        }else {
            req.getRequestDispatcher("/WEB-INF/jsp/commodity/commodity.jsp").forward(req,resp);

        }
    }

    private void setCommodityQuery(HttpServletRequest req, HttpServletResponse resq) throws UnsupportedEncodingException {

        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String brandName = req.getParameter("brandName");
        System.out.println(brandName);
        if (StringUtils.isNotBlank(name)){
            commodityQuery.setName(name);
        }
        if (StringUtils.isNotBlank(minPrice)){
            commodityQuery.setMinPrice(Double.valueOf(minPrice));
        }
//
        if (StringUtils.isNotBlank(maxPrice)){
            commodityQuery.setMaxPrice(Double.valueOf(maxPrice));
        }

        if (StringUtils.isNotBlank(brandName) && !brandName.equals("全部")){
            commodityQuery.setBrandName(brandName);
        }
    }
}
