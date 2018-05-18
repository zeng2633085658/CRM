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

public class OrdersServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String custid;
    private String orderType;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        UsersVo u=(UsersVo) session.getAttribute("user");
        if(u==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        //获取客户编号
        custid=request.getParameter("custid");
        orderType=request.getParameter("orderType");
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
            List<CustomerInfoVo> customerInfo=base.findCustomerInfos();
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("customerInfo",customerInfo);
            request.setAttribute("usersVos",usersVos);

            //生成订单号
            String orderId=base.findOrderId();
            session.setAttribute("orderId",orderId);


            List<UnitVo> unit=base.findUnit();
            List<ProductVo> product=base.findPageProduct(0,1000000);

            //取第一个商品的信息
            ProductVo productVo=base.findProductId(product.get(0).getProdid());

            request.setAttribute("productVo",productVo);
            request.setAttribute("product",product);
            request.setAttribute("unit",unit);
            request.setAttribute("orderId",orderId);
            request.setAttribute("newTime",ContextUtils.dateToStrShort(new Date()));

            request.getRequestDispatcher("/ordAdd.jsp").forward(request,response);
        }else if(action.equals("add")){
            OrdersVo o=new OrdersVo();
            o.setOrderId(request.getParameter("orderId").trim());
            o.setCustid(Integer.parseInt(custid));
            o.setUserid(Integer.parseInt(request.getParameter("userid").trim()));
            o.setOrderType("销售出库");
            o.setOrderStatus("1");
            o.setProcess("已下单");
            o.setOperator(u.getUserid()+"");
            o.setRemark(request.getParameter("remark").trim());
            o.setOprtime(request.getParameter("oprtime"));

            OrderDetailVo o1=new OrderDetailVo();
            o1.setOrderId(o.getOrderId());
            o1.setProdid(request.getParameter("prodid"));
            o1.setStatus(request.getParameter("status"));
            o1.setSaleMoney(Float.parseFloat(request.getParameter("saleMoney").trim()));
            o1.setProdCount(Integer.parseInt(request.getParameter("prodCount")));


            o1.setUnitId(Integer.parseInt(request.getParameter("unitId1")));
            o1.setRegPerson(request.getParameter("regPerson").trim());
            o1.setRegPassword(request.getParameter("regPassword").trim());
            o1.setServicePeriod("");
            o1.setExpireDate(ContextUtils.dateToStrShort1(new Date()));

            o.setOperator(u.getUserid()+"");
            o1.setRemark(request.getParameter("remark").trim());

            if(!o1.getStatus().equals("销售")){
                o.setTotalMoney(0);
                o1.setTotalMoney(0);
            }else {
                o.setTotalMoney(o1.getSaleMoney()*o1.getProdCount());
                o1.setTotalMoney(o1.getSaleMoney()*o1.getProdCount());
            }




            //判断订单是否存在
            if(base.isOrder(o.getOrderId())){
                base.addOrders(o);
                session.setAttribute("orderId1",o.getOrderId());
            }
            //新增明细
            base.addOrderDetail(o1);

            //计算订单金额
            base.updateOrdersTotalMoney(o.getOrderId());

            //计算商品库存
            base.updateProductCounts(o1.getProdid(),o1.getProdCount());


        }else if(action.equals("test")){
            System.out.println(new BaseDAO().findOrderId());
        }else if(action.equals("price")){
            String prodid = request.getParameter("prodid");
            ProductVo prod = base.findProductId(prodid);
            System.out.println(prodid);
            JSONObject json = new JSONObject();
            json.put("price", prod.getSalePrice());
            json.put("unitId", prod.getProdUnit());
            json.put("unitName", prod.getProdUnitName());

            out.print(json.toJSONString());
        }else if(action.equals("status")){
            String orderId=request.getParameter("orderId");
            int orderStatus= Integer.parseInt(request.getParameter("orderStatus"));
            OrdersVo ordersVo=base.findOrdersId(orderId);
            int orderStatusOld=Integer.parseInt(ordersVo.getOrderStatus());
            if(orderStatus==orderStatusOld){
                out.print("已是该状态");
                return;
            }
           if(orderStatus==orderStatusOld+1){
               base.updateOrders(orderStatus+"",orderId);
           }else {
               out.print("请按照,已收款--实施中--已完成");
           }



        }else if(action.equals("del")){
            String orderId=request.getParameter("orderId");
            String orderStatus=request.getParameter("orderStatus");
            if(!orderStatus.equals("1")){
                out.print("只能删除已下单的订单");
                return;
            }
            //查询订单里的全部明细
            List<OrderDetailVo> orderDetailVos=base.findOrderDetailVos(orderId);
            for(OrderDetailVo o:orderDetailVos){
                //修改商品数量
                base.updateProductCounts(o.getProdid(),-o.getProdCount());
                //删除订单明细
                base.del("OrderDetail","DetailId",o.getDetailId()+"");
            }
            //删除订单
            base.del("Orders","orderId",orderId);
        }else if(action.equals("addCount")){ //新增入库
            OrdersVo o=new OrdersVo();
            o.setOrderId(request.getParameter("orderId").trim());
            o.setCustid(Integer.parseInt(request.getParameter("supplierId")));
            o.setUserid(Integer.parseInt(request.getParameter("userid").trim()));
            o.setOrderType("采购入库");
            o.setOrderStatus("1");
            o.setProcess(request.getParameter("status"));
            o.setOperator(u.getUserid()+"");
            o.setRemark(request.getParameter("remark").trim());


            OrderDetailVo o1=new OrderDetailVo();
            o1.setOrderId(o.getOrderId());
            o1.setProdid(request.getParameter("prodid"));
            o1.setStatus(request.getParameter("status"));
            o1.setSaleMoney(Float.parseFloat(request.getParameter("saleMoney").trim()));
            o1.setProdCount(Integer.parseInt(request.getParameter("prodCount")));


            o1.setUnitId(Integer.parseInt(request.getParameter("unitId1")));
            o1.setRegPerson(request.getParameter("regPerson").trim());
            o1.setRegPassword(request.getParameter("regPassword").trim());
            o1.setServicePeriod("");
            o1.setExpireDate(ContextUtils.dateToStrShort1(new Date()));

            o.setOperator(u.getUserid()+"");
            o1.setRemark(request.getParameter("remark").trim());

            o.setTotalMoney(o1.getSaleMoney()*o1.getProdCount());
            o1.setTotalMoney(o1.getSaleMoney()*o1.getProdCount());


            //判断订单是否存在
            if(base.isOrder(o.getOrderId())){
                base.addOrders(o);
                session.setAttribute("orderId1",o.getOrderId());
            }
            //新增明细
            base.addOrderDetail(o1);

            //计算订单金额
            base.updateOrdersTotalMoney(o.getOrderId());

            //计算商品库存
            base.updateProductCounts(o1.getProdid(),-o1.getProdCount());

        }else if(action.equals("countInit")){
            List<SupplierVo> supplierVos=base.findPageSupplier(0,1000);
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("supplierVos",supplierVos);
            request.setAttribute("usersVos",usersVos);

            //生成订单号
            String orderId=base.findOrderId();
            session.setAttribute("orderId",orderId);

            request.setAttribute("time",ContextUtils.dateToStrShort(new Date()));

            List<UnitVo> unit=base.findUnit();
            List<ProductVo> product=base.findPageProduct(0,1000000);
            request.getRequestDispatcher("/proAddCount.jsp").forward(request,response);
        }else if(action.equals("supplier")){
            String supplierId=request.getParameter("supplierId");
            System.out.println(supplierId);
            List<ProductVo> p=base.findSupplierIdProduct(supplierId);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("product",p);
            out.print(jsonObject.toJSONString());
            System.out.println(jsonObject.toJSONString());
        }else if(action.equals("delSupplier")){
            String orderId=request.getParameter("orderId");
            String orderStatus=request.getParameter("orderStatus");
            if(!orderStatus.equals("1")){
                out.print("只能删除已采购的入库单");
                return;
            }
            //查询订单里的全部明细
            List<OrderDetailVo> orderDetailVos=base.findOrderDetailVos(orderId);
            for(OrderDetailVo o:orderDetailVos){
                //修改商品数量
                base.updateProductCounts(o.getProdid(),o.getProdCount());
                //删除入库明细
                base.del("OrderDetail","DetailId",o.getDetailId()+"");
            }
            //删除订单
            base.del("Orders","orderId",orderId);
        }else if(action.equals("statusSupplier")){
            String orderId=request.getParameter("orderId");
            int orderStatus= Integer.parseInt(request.getParameter("orderStatus"));
            OrdersVo ordersVo=base.findOrdersId(orderId);
            int orderStatusOld=Integer.parseInt(ordersVo.getOrderStatus());
            if(orderStatus==orderStatusOld){
                out.print("已是该状态");
                return;
            }
            if(orderStatus==orderStatusOld+1){
                base.updateOrders(orderStatus+"",orderId);
            }else {
                out.print("请按照,已入库--已审核");
            }
        }else if(action.equals("prices")){
            String prodid = request.getParameter("prodid");
            ProductVo prod = base.findProductId(prodid);
            System.out.println(prodid);
            JSONObject json = new JSONObject();
            json.put("price", prod.getInPrice());
            json.put("unitId", prod.getProdUnit());
            json.put("unitName", prod.getProdUnitName());
            out.print(json.toJSONString());
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
        int cnt=0;
        if(orderType==null) {
             cnt = base.findCountOrders(custid);
        }else{
            cnt = base.findCountOrders1("采购入库");
        }
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<OrdersVo> list=null;
        if(orderType==null){
           list = base.findPageOrders(pager.getStartRow(), pager.getPageRow(),custid,"销售出库");
        }else {
           list = base.findPageOrders(pager.getStartRow(), pager.getPageRow(),null,"采购入库");
        }

        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
