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

public class FinanceServlet extends HttpServlet {
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
            FinanceVo f=new FinanceVo();
            f.setOrderId(request.getParameter("orderId").trim());
            f.setProdid(request.getParameter("prodid").trim());
            f.setPaidtypeid(Integer.parseInt(request.getParameter("paidtypeid")));
            String remainMoney=request.getParameter("remainMoney");
            if(remainMoney.equals("")){
                remainMoney="0";
            }
            f.setRemainMoney(Float.parseFloat(remainMoney));
            String paidMoney=request.getParameter("paidMoney");
            if(paidMoney.equals("")){
                paidMoney="0";
            }
            f.setPaidMoney(Float.parseFloat(paidMoney));
            String orderMoney=request.getParameter("orderMoney");
            if(remainMoney.equals("")){
                remainMoney="0";
            }
            f.setOrderMoney(Float.parseFloat(orderMoney));

            f.setPaidPerson(request.getParameter("paidPerson").trim());
            f.setInbank(request.getParameter("inbank").trim());
            f.setBankAccount(request.getParameter("bankAccount").trim());
            f.setOutbank(request.getParameter("outbank").trim());
            f.setWarrant(request.getParameter("warrant").trim());
            f.setPaidTime(request.getParameter("paidTime".trim()));
            f.setPaidinTime(request.getParameter("paidinTime").trim());
            f.setInvalid(request.getParameter("invalid").trim());
            f.setUserid(user.getUserid());
            f.setOprtime(ContextUtils.dateToStrLong(new Date()));
            f.setOprType(request.getParameter("oprType").trim());
            base.addFinance(f);
            list(request,response);
        }else if(action.equals("update")){
            List<ProductVo> product=base.findPageProduct(0,100000);
            List<PaidTypeVo> paidType=base.findPagePaidType(0,100000);
            List<OrdersVo> orders=base.findOrders();
            request.setAttribute("product",product);
            request.setAttribute("paidType",paidType);
            request.setAttribute("orders",orders);
            System.out.println(request.getParameter("financeId"));
            FinanceVo f=base.findFinanceId(Integer.parseInt(request.getParameter("financeId")));
            request.setAttribute("finance",f);
            request.getRequestDispatcher("/finUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            FinanceVo f=new FinanceVo();
            f.setFinanceId(Integer.parseInt(request.getParameter("financeId")));
            f.setOrderId(request.getParameter("orderId").trim());
            f.setProdid(request.getParameter("prodid").trim());
            f.setPaidtypeid(Integer.parseInt(request.getParameter("paidtypeid")));
            String remainMoney=request.getParameter("remainMoney");
            if(remainMoney.equals("")){
                remainMoney="0";
            }
            f.setRemainMoney(Float.parseFloat(remainMoney));
            String paidMoney=request.getParameter("paidMoney");
            if(paidMoney.equals("")){
                paidMoney="0";
            }
            f.setPaidMoney(Float.parseFloat(paidMoney));
            String orderMoney=request.getParameter("orderMoney");
            if(remainMoney.equals("")){
                remainMoney="0";
            }
            f.setOrderMoney(Float.parseFloat(orderMoney));

            f.setPaidPerson(request.getParameter("paidPerson").trim());
            f.setInbank(request.getParameter("inbank").trim());
            f.setBankAccount(request.getParameter("bankAccount").trim());
            f.setOutbank(request.getParameter("outbank").trim());
            f.setWarrant(request.getParameter("warrant").trim());
            f.setPaidTime(request.getParameter("paidTime".trim()));
            f.setPaidinTime(request.getParameter("paidinTime").trim());
            f.setInvalid(request.getParameter("invalid").trim());
            f.setUserid(user.getUserid());
            f.setOprtime(ContextUtils.dateToStrLong(new Date()));
            f.setOprType(request.getParameter("oprType").trim());
            base.updateFinance(f);
            list(request,response);
        }else if(action.equals("init")){
            List<ProductVo> product=base.findPageProduct(0,100000);
            List<PaidTypeVo> paidType=base.findPagePaidType(0,100000);
            List<OrdersVo> orders=base.findOrders();
            request.setAttribute("product",product);
            request.setAttribute("paidType",paidType);
            request.setAttribute("orders",orders);
            request.getRequestDispatcher("/finAdd.jsp").forward(request,response);
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
        int cnt =base.findCount("Finance");
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<FinanceVo> list = base.findPageFinance(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
