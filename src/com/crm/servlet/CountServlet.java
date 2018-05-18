package com.crm.servlet;

import com.crm.dao.zyl.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Lin on 2018/4/8.
 */
public class CountServlet extends HttpServlet {

    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String action=request.getParameter("action");
        if(action.equals("xs")){
            //订单总数
            int ordcount = base.findord();
            request.setAttribute("ordcount",ordcount);

            //订单完成总数
            int ordend = base.findordend();
            request.setAttribute("ordend",ordend);

            //订单总金额
            int ordsum = base.findordsum();
            request.setAttribute("ordsum",ordsum);

            request.getRequestDispatcher("xscount.jsp").forward(request, response);
        }else if (action.equals("ht")){
            int h1 = base.findcon("已签订");
            request.setAttribute("h1",h1);
            int h2 = base.findcon("已付款");
            request.setAttribute("h2",h2);
            int h3 = base.findcon("服务中");
            request.setAttribute("h3",h3);
            int h4 = base.findcon("已完成");
            request.setAttribute("h4",h4);
            request.getRequestDispatcher("htcount.jsp").forward(request, response);
        }else if (action.equals("pg")) {
            int jobcount = base.findjob();
            request.setAttribute("jobcount",jobcount);
            float jobok = base.findjobok();
            request.setAttribute("jobok",jobok);
            float jobgood = jobok/jobcount*100;
            request.setAttribute("jobgood",jobgood);

            request.getRequestDispatcher("pgcount.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}