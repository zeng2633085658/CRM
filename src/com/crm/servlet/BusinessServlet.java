package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
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
import java.util.List;

public class BusinessServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String custId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        UsersVo u=(UsersVo) session.getAttribute("user");
        if(u==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //获取客户编号
        custId=request.getParameter("custId");
        if(custId!=null){
            session.setAttribute("custId",custId);
        }else{
            custId =(String) session.getAttribute("custId");
        }
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("init")){
            List<ProductVo> product=base.findPageProduct(0,100000);
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("product",product);
            request.setAttribute("usersVos",usersVos);
            request.getRequestDispatcher("/busAdd.jsp").forward(request,response);
        }else if(action.equals("add")){
            BusinessVo b=new BusinessVo();
            b.setBusDate(request.getParameter("busDate"));
            b.setProdid(request.getParameter("prodid"));
            b.setChatContent(request.getParameter("chatContent").trim());
            b.setChatResult(request.getParameter("chatResult").trim());
            b.setCustid(Integer.parseInt(custId));
            b.setCustContact(request.getParameter("custContact").trim());
            b.setPhone(request.getParameter("phone").trim());
            System.out.println(request.getParameter("userid"));
            b.setUserid(Integer.parseInt(request.getParameter("userid")));
            b.setModule(request.getParameter("module").trim());
            b.setModuleState(request.getParameter("moduleState").trim());
            b.setModuleMoney(Float.parseFloat(request.getParameter("moduleMoney")));
            b.setRemark(request.getParameter("remark").trim());
            base.addBusiness(b);
            list(request,response);
        }else if(action.equals("update")){
            List<ProductVo> product=base.findPageProduct(0,100000);
            List<UsersVo> usersVos=base.findUsers();
            request.setAttribute("product",product);
            request.setAttribute("usersVos",usersVos);

            BusinessVo b=base.findBusinessId(Integer.parseInt(request.getParameter("businessId")));
            request.setAttribute("business",b);
            request.getRequestDispatcher("/busUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            BusinessVo b=new BusinessVo();
            b.setBusinessId(Integer.parseInt(request.getParameter("businessIds")));
            b.setBusDate(request.getParameter("busDate"));
            b.setProdid(request.getParameter("prodid"));
            b.setChatContent(request.getParameter("chatContent").trim());
            b.setChatResult(request.getParameter("chatResult").trim());
            b.setCustid(Integer.parseInt(custId));
            b.setCustContact(request.getParameter("custContact").trim());
            b.setPhone(request.getParameter("phone").trim());
            b.setUserid(Integer.parseInt(request.getParameter("userid")));
            b.setModule(request.getParameter("module").trim());
            b.setModuleState(request.getParameter("moduleState").trim());
            b.setModuleMoney(Float.parseFloat(request.getParameter("moduleMoney")));
            b.setRemark(request.getParameter("remark").trim());
            base.updateBusiness(b);
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
        int cnt =base.findCountBusiness(custId);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<BusinessVo> list = base.findPageBusiness(pager.getStartRow(), pager.getPageRow(),custId);
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
