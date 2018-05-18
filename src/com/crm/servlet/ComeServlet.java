package com.crm.servlet;

import com.crm.dao.zyl.BaseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ComeServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private int usercount;
    private int cuscount;
    private int procount;
    private int ordcount;
    private int jobcount;
    private int weekcount;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        //用户总数
        usercount = base.findUsers();
        System.out.println(usercount);
        request.setAttribute("usercount",usercount);
        //客户总数
        cuscount = base.findcus();
        request.setAttribute("cuscount",cuscount);
        //商品总数
        procount = base.findpro();
        request.setAttribute("procount",procount);
        //订单总数
        ordcount = base.findord();
        request.setAttribute("ordcount",ordcount);
        //派工总数
        jobcount = base.findjob();
        request.setAttribute("jobcount",jobcount);
        //工作周报总数
        weekcount = base.findweek();
        request.setAttribute("weekcount",weekcount);

        request.getRequestDispatcher("welcome.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
