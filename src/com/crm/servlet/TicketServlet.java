package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.ContextUtils;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class TicketServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        UsersVo user=(UsersVo) session.getAttribute("user");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("add")){
            TicketVo t=new TicketVo();
            t.setTicketDate(request.getParameter("ticketDate"));
            t.setOrderid(request.getParameter("orderid").trim());
            t.setCustid(Integer.parseInt(request.getParameter("custid")));
            String ticketMoney=request.getParameter("ticketMoney");
            if(ticketMoney==""){
                ticketMoney="0";
            }
            t.setTicketMoney(Float.parseFloat(ticketMoney));
            t.setTicketComp(request.getParameter("ticketComp").trim());
            t.setUserid(user.getUserid());
            t.setOprtime(ContextUtils.dateToStrLong(new Date()));
            t.setRemark(request.getParameter("remark"));
            base.addTicket(t);
            list(request,response);
        }else if(action.equals("update")){
            List<OrdersVo> orders=base.findOrders();
            List<CustomerInfoVo> customerInfo=base.findPageCustomerInfo(0,100000);
            request.setAttribute("customerInfo",customerInfo);
            request.setAttribute("orders",orders);
            TicketVo t=base.findTicketId(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("ticket",t);
            request.getRequestDispatcher("/ticUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            TicketVo t=new TicketVo();
            t.setId(Integer.parseInt(request.getParameter("id")));
            t.setTicketDate(request.getParameter("ticketDate"));
            t.setOrderid(request.getParameter("orderid").trim());
            t.setCustid(Integer.parseInt(request.getParameter("custid")));
            String ticketMoney=request.getParameter("ticketMoney");
            if(ticketMoney==""){
                ticketMoney="0";
            }
            t.setTicketMoney(Float.parseFloat(ticketMoney));
            t.setTicketComp(request.getParameter("ticketComp").trim());
            t.setUserid(user.getUserid());
            t.setOprtime(ContextUtils.dateToStrLong(new Date()));
            t.setRemark(request.getParameter("remark"));
            base.updateTicket(t);
            list(request,response);
        }else if(action.equals("init")){
            List<OrdersVo> orders=base.findOrders();
            List<CustomerInfoVo> customerInfo=base.findPageCustomerInfo(0,100000);
            request.setAttribute("customerInfo",customerInfo);
            request.setAttribute("orders",orders);
            request.setAttribute("time",ContextUtils.dateToStrShort(new Date()));
            request.getRequestDispatcher("/ticAdd.jsp").forward(request,response);
        }else if(action.equals("select")){
            String custid=request.getParameter("custid");
            List<OrdersVo> ordersVos=base.findOrderCustid(custid);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("ordersVos",ordersVos);
            out.print(jsonObject.toJSONString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //分页查询
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
        int cnt =base.findCount("Ticket");
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<TicketVo> list = base.findPageTicket(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
