package com._520.webhomework.commodity.servlet;


import com._520.webhomework.commodity.domain.Commodity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 处理价格与删除商品
@WebServlet("/settlement")
public class SettlementServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 获取请求参数
        List<Commodity> list = (List<Commodity>) req.getSession().getAttribute("COMMODITY_IN_SESSION");
        String deleteName = req.getParameter("name");
        // 先删除，在计算总价
        // 判断是否有删除
        if (deleteName != null){
            delete(list,deleteName);
        }
        // 计算总价
        double count = getTotal(list);

//        System.out.println("总价为：" + count + "deleteName = " + deleteName);
        // 将总价放入session中
        req.getSession().setAttribute("TOTAL_IN_SESSION",count);
        // 控制界面跳转
        req.getRequestDispatcher("/jsp/shopping.jsp").forward(req,resp);
    }

    /**
     *  计算总价
     * @param list
     * @return
     */
    private static int getTotal(List<Commodity> list){
        int total = 0;      // 总价

        return total;
    }

    /**
     * 删除指定名称的书籍
     * @param list
     * @param deleteName
     */
    private static void delete(List<Commodity> list, String deleteName){
        for (Commodity comm:list
             ) {
            if (deleteName.equals(comm.getName())){ // 找到指定名称的商品
                list.remove(comm);  // 删除
                break;
            }
        }
    }
}
