package com._520.webhomework.commodity.servlet;


import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.IShoppingDAO;
import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.dao.impl.ShoppingDAOImpl;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.domain.Shopping;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// 获取商品并加入购物车
@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {
    // 使用list模拟购物车
    private static IShoppingDAO dao;
    private static ICommodityDAO commodityDAO;

    @Override
    public void init() throws ServletException {
        // 在启动Tomcat时初始化一次
        dao = new ShoppingDAOImpl();
        commodityDAO = new CommodityDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String pwd = req.getParameter("pwd");
        if ("save".equals(pwd)){
            try {
                this.save(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("delete".equals(pwd)){
            try {
                this.delete(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("removeAll".equals(pwd)){
            try {
                this.removeAll(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("update".equals(pwd)){
            this.update(req,resp);
        }else {
            // 将list存入session
            req.getSession().setAttribute("SHOPPING_IN_SESSION",getShopping());
            req.getSession().setAttribute("PRICE_IN_SESSION",getAllPrice(getShopping()));
            // 跳转
            req.getRequestDispatcher("/WEB-INF/jsp/shopping/shoppingCart.jsp").forward(req,resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String bookName = req.getParameter("bookName");
        Shopping shopping = dao.getByName(bookName);
        Integer updateCount = Integer.valueOf( req.getParameter("updateCount"));
        System.out.println("name = " + bookName +"数量 = " + updateCount);
        shopping.setName(shopping.getName());
        shopping.setCount(updateCount);
        shopping.setPrice(shopping.getPrice());
        shopping.setTotalPrice(shopping.getTotalPrice());
        System.out.println(shopping);
        dao.update(shopping,shopping.getName());
        req.setAttribute("MSG_IN_SESSION","修改成功！");
        req.getRequestDispatcher("/WEB-INF/jsp/shopping/success.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求参数
        String bookName = req.getParameter("bookName");

        // 判断当前书籍有没有在购物车中
        Shopping shopping = dao.getByName(bookName);
        Commodity commodity = commodityDAO.getByName(bookName);
        System.out.println("商品：" + commodity);
        if (shopping == null){
            System.out.println("增加");
            // 没有就新创建一个对象
            Shopping newShopping = new Shopping();
            newShopping.setName(bookName);
            newShopping.setCount(1);
            newShopping.setPrice(commodity.getPrice());
            newShopping.setTotalPrice(commodity.getPrice());
            dao.save(newShopping);
        }else {
            System.out.println("修改");
            // 有就修改数量
            shopping.setName(shopping.getName());
            shopping.setCount(shopping.getCount() + 1);
            shopping.setPrice(shopping.getPrice());
            shopping.setTotalPrice(shopping.getTotalPrice());
            System.out.println(shopping);
            dao.update(shopping,shopping.getName());
        }
        req.setAttribute("MSG_IN_SESSION","加入购物车成功！");
        req.getRequestDispatcher("/WEB-INF/jsp/shopping/success.jsp").forward(req,resp);

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求参数
        String bookName = req.getParameter("bookName");
        System.out.println("bookname = " + bookName);
        dao.delete(bookName);
        req.setAttribute("MSG_IN_SESSION","删除成功！");
        req.getRequestDispatcher("/WEB-INF/jsp/shopping/success.jsp").forward(req,resp);
    }

    private void removeAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        dao.removeAll();
        req.setAttribute("MSG_IN_SESSION","清空购物车成功！");
        req.getRequestDispatcher("/WEB-INF/jsp/shopping/success.jsp").forward(req,resp);
    }
    private static List<Shopping> getShopping(){
        return dao.listAll();
    }

    private static Double getAllPrice(List<Shopping> list){
        Double price = 0.0;
        for (Shopping s:list
             ) {
            price += s.getTotalPrice();
        }
        return price;
    }
}
