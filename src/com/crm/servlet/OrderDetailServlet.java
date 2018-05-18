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


public class OrderDetailServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String orderId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        //获取订单编号
        orderId=request.getParameter("orderId");
        if(orderId!=null){
            session.setAttribute("orderId",orderId);
        }else{
            orderId =(String) session.getAttribute("orderId");
        }
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("init")){

            List<UnitVo> unit=base.findUnit();
            List<ProductVo> product=base.findPageProduct(0,1000000);

            request.setAttribute("product",product);
            request.setAttribute("unit",unit);
            request.getRequestDispatcher("/odAdd.jsp").forward(request,response);
        }else if(action.equals("update")){

            List<UnitVo> unit=base.findUnit();
            List<ProductVo> product=base.findPageProduct(0,1000000);
            request.setAttribute("product",product);
            request.setAttribute("unit",unit);
            String detailId=request.getParameter("detailId");
            OrderDetailVo ordd=base.findOrderDetailId(detailId);
            request.setAttribute("ordd",ordd);
            request.getRequestDispatcher("/odUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            OrderDetailVo o=new OrderDetailVo();
            o.setDetailId(Integer.parseInt(request.getParameter("detailId")));
            o.setOrderId(orderId);
            o.setProdid(request.getParameter("prodid"));
            o.setStatus(request.getParameter("status"));
            o.setSaleMoney(Float.parseFloat(request.getParameter("saleMoney").trim()));
            o.setUnitId(Integer.parseInt(request.getParameter("unitId")));
            o.setRegPerson(request.getParameter("regPerson").trim());
            o.setRegPassword(request.getParameter("regPassword").trim());
            o.setServicePeriod(request.getParameter("servicePeriod").trim());
            o.setExpireDate(request.getParameter("expireDate").trim());
            int prodCount=Integer.parseInt(request.getParameter("prodCount"));

            o.setProdCount(prodCount);
            o.setTotalMoney(Float.parseFloat(request.getParameter("saleMoney"))*Float.parseFloat(request.getParameter("prodCount")));
            o.setOperator(request.getParameter("operator").trim());
            o.setRemark(request.getParameter("remark").trim());

            //订单表信息
            OrdersVo orders=base.findOrdersId(o.getOrderId());


            base.updateOrdersTotalMoney(o.getProdid());


            //修改订单表的总金额
            base.updateOrdersTotalMoney(o.getOrderId());

            //修改时改变商品的数量
//            base.updateProductCounts(o.getProdid(),o.getDetailId(),o.getProdCount());
            base.updateOrderDetail(o);


            list(request,response);
        }else if(action.equals("add")){
            OrderDetailVo o=new OrderDetailVo();
            o.setOrderId(orderId);
            o.setProdid(request.getParameter("prodid"));
            o.setStatus(request.getParameter("status"));
            o.setSaleMoney(Float.parseFloat(request.getParameter("saleMoney").trim()));
            o.setUnitId(Integer.parseInt(request.getParameter("unitId")));
            o.setRegPerson(request.getParameter("regPerson").trim());
            o.setRegPassword(request.getParameter("regPassword").trim());
            o.setServicePeriod(request.getParameter("servicePeriod").trim());
            o.setExpireDate(request.getParameter("expireDate").trim());
            o.setProdCount(Integer.parseInt(request.getParameter("prodCount")));
            o.setTotalMoney(Float.parseFloat(request.getParameter("saleMoney").trim())*Integer.parseInt(request.getParameter("prodCount")));
            o.setOperator(request.getParameter("operator").trim());
            o.setRemark(request.getParameter("remark").trim());

            //订单表信息
            OrdersVo orders=base.findOrdersId(o.getOrderId());



            base.updateOrdersTotalMoney(o.getProdid());
            base.addOrderDetail(o);
            //修改订单表的总金额
            if(o.getStatus().equals("销售")){
                base.updateOrdersTotalMoney(o.getOrderId());
            }



            list(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    //分页查询
    public void list(HttpServletRequest request, HttpServletResponse response)
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
            limit="500";
        }
        int cnt =base.findCountOrderDetail(orderId);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<OrderDetailVo> list= base.findPageOrderDetail(pager.getStartRow(), pager.getPageRow(),orderId);
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
