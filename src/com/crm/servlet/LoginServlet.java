package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.AesUtils;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//登录
public class LoginServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        password = AesUtils.encryptStr(password, AesUtils.SECRETKEY);
        UsersVo usersVo = base.userLogin(username);
        if (usersVo!=null) {
            if (usersVo.getPassword().equals(password)) {
                if (usersVo.getStatus() == 1) {
                    HttpSession session=request.getSession();
                    session.setAttribute("user",usersVo);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg","账号已被冻结,请联系系统管理员");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
