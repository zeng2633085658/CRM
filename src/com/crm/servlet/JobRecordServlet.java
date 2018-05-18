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

public class JobRecordServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String custid;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        //获取客户编号
        custid=request.getParameter("custid");
        System.out.println(custid);
        if(custid!=null){
            session.setAttribute("custid",custid);
        }else{
            custid =(String) session.getAttribute("custid");
        }
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("init")){
            List<ProductVo> product=base.findPageProduct(0,100000);
            List<CustomerInfoVo> customerInfo=base.findPageCustomerInfo(0,100000);
            List<OrdersVo> orders=base.findOrders();
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("usersVos",usersVos);
            request.setAttribute("product",product);
            request.setAttribute("orders",orders);
            request.setAttribute("customerInfo",customerInfo);
            request.getRequestDispatcher("/jobAdd.jsp").forward(request,response);
        }else if(action.equals("update")){

            JobRecordVo j=base.findJobRecordId(Integer.parseInt(request.getParameter("jobId")));

            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("usersVos",usersVos);

            List<ProductVo> product=base.findPageProduct(0,100000);
            List<CustomerInfoVo> customerInfo=base.findPageCustomerInfo(0,100000);
            List<OrdersVo> orders=base.findOrders();
            request.setAttribute("product",product);
            request.setAttribute("orders",orders);
            request.setAttribute("customerInfo",customerInfo);

            request.setAttribute("jobRecord",j);
            request.getRequestDispatcher("/jobUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            JobRecordVo j=new JobRecordVo();
            j.setJobId(Integer.parseInt(request.getParameter("jobIds")));
            j.setOrderId(request.getParameter("orderId"));
            j.setJobDate(request.getParameter("jobDate"));
            j.setProdid(request.getParameter("prodid"));
            j.setCustid(Integer.parseInt(custid));
            j.setJobContent(request.getParameter("jobContent").trim());
            j.setCallback(request.getParameter("callback").trim());
            j.setUserid(request.getParameter("userid").trim());
            j.setCustEval(request.getParameter("custEval").trim());
            j.setCustSign(request.getParameter("custSign").trim());
            j.setStartTime(request.getParameter("startTime"));
            j.setEndTime(request.getParameter("endTime"));
            j.setWorkDay(Integer.parseInt(request.getParameter("workDay")));
            String busMoney=request.getParameter("busMoney");
            if (busMoney.equals("")){
                busMoney="0";
            }
            j.setBusMoney(Float.parseFloat(busMoney));
            String attachMoney=request.getParameter("attachMoney");
            if (attachMoney.equals("")){
                attachMoney="0";
            }
            j.setAttachMoney(Float.parseFloat(attachMoney));
            base.updateJobRecord(j);
            list(request,response);
        }else if(action.equals("add")){
            JobRecordVo j=new JobRecordVo();
            j.setOrderId(request.getParameter("orderId"));
            j.setJobDate(request.getParameter("jobDate"));
            j.setProdid(request.getParameter("prodid"));
            j.setCustid(Integer.parseInt(custid));
            j.setJobContent(request.getParameter("jobContent").trim());
            j.setCallback(request.getParameter("callback").trim());
            j.setUserid(request.getParameter("userid").trim());
            j.setCustEval(request.getParameter("custEval").trim());
            j.setCustSign(request.getParameter("custSign").trim());
            j.setStartTime(request.getParameter("startTime"));
            j.setEndTime(request.getParameter("endTime"));
            j.setWorkDay(Integer.parseInt(request.getParameter("workDay")));
            String busMoney=request.getParameter("busMoney");
            if (busMoney.equals("")){
                busMoney="0";
            }
            j.setBusMoney(Float.parseFloat(busMoney));
            String attachMoney=request.getParameter("attachMoney");
            if (attachMoney.equals("")){
                attachMoney="0";
            }
            j.setAttachMoney(Float.parseFloat(attachMoney));
            base.addJobRecord(j);
            list(request,response);
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
            limit="50";
        }
        int cnt =base.findCountJobRecord(custid);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<JobRecordVo> list = base.findPageJobRecord(pager.getStartRow(), pager.getPageRow(),custid);
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
