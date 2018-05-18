package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.CustomerInfoVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CustomerInfoServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("add")){
            CustomerInfoVo c=new CustomerInfoVo();
            c.setCustname(request.getParameter("title").trim());
            c.setCusttype(request.getParameter("custtype").trim());
            c.setBankAccount(request.getParameter("bankAccount").trim());
            c.setBankName(request.getParameter("bankName").trim());
            c.setContact(request.getParameter("Contact").trim());
            c.setPhone(request.getParameter("phone").trim());
            c.setTicketName(request.getParameter("TicketName").trim());
            c.setTicketAddr(request.getParameter("TicketAddr").trim());
            c.setTicketTel(request.getParameter("TicketTel").trim());
            c.setTaxNo(request.getParameter("TaxNo").trim());
            c.setCustState(request.getParameter("custState").trim());
            c.setUserid(Integer.parseInt(request.getParameter("userid")));
            c.setSource(request.getParameter("source").trim());
            base.addCustomerInfo(c);
            list(request,response);
        }else if(action.equals("update")){
            String custId=request.getParameter("custId").trim();
            CustomerInfoVo c=base.findCustomerInfoId(Integer.parseInt(custId));
            request.setAttribute("cus",c);
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("usersVos",usersVos);
            request.getRequestDispatcher("/cusUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            CustomerInfoVo c=new CustomerInfoVo();
            c.setCustId(Integer.parseInt(request.getParameter("custId")));
            c.setCustname(request.getParameter("title").trim());
            c.setCusttype(request.getParameter("custtype").trim());
            c.setBankAccount(request.getParameter("bankAccount").trim());
            c.setBankName(request.getParameter("bankName").trim());
            c.setContact(request.getParameter("Contact").trim());
            c.setPhone(request.getParameter("phone").trim());
            c.setTicketName(request.getParameter("TicketName").trim());
            c.setTicketAddr(request.getParameter("TicketAddr").trim());
            c.setTicketTel(request.getParameter("TicketTel").trim());
            c.setTaxNo(request.getParameter("TaxNo").trim());
            c.setCustState(request.getParameter("custState").trim());
            c.setUserid(Integer.parseInt(request.getParameter("userid")));
            c.setSource(request.getParameter("source").trim());
            base.updateCustomerInfo(c);
            list(request,response);
        }else if(action.equals("init")){
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("usersVos",usersVos);
            request.getRequestDispatcher("/cusAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
   // 分页查询
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        PageObject pager = new PageObject();
        String limit=request.getParameter("limit");
        System.out.println(limit);
        String page=request.getParameter("page");
        if(page==null){
            page="1";
        }
        if(limit==null){
            limit="5";
        }
        int cnt =base.findCountCustomerInfo();
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<CustomerInfoVo> list = base.findPageCustomerInfo(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);
        System.out.println(json);
    }
}
