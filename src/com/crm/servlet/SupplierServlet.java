package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.DepVo;
import com.crm.vo.SupplierVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SupplierServlet extends HttpServlet {
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
            SupplierVo s=new SupplierVo();
            s.setSupplierName(request.getParameter("title").trim());
            s.setBankAccount(request.getParameter("bankcardno").trim());
            s.setBankName(request.getParameter("bankname").trim());
            s.setContact(request.getParameter("contact").trim());
            s.setPhone(request.getParameter("phone").trim());
            s.setAddr(request.getParameter("addr").trim());
            s.setRemark(request.getParameter("remark").trim());
            base.addSupplier(s);
            list(request,response);
        }else if(action.equals("update")){
            String supplierId=request.getParameter("supplierId");
            SupplierVo s=base.findSupplierId(Integer.parseInt(supplierId));
            request.setAttribute("sup",s);
            request.getRequestDispatcher("/supUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            SupplierVo s=new SupplierVo();
            s.setSupplierId(Integer.parseInt(request.getParameter("supplierId")));
            s.setSupplierName(request.getParameter("title").trim());
            s.setBankAccount(request.getParameter("bankcardno").trim());
            s.setBankName(request.getParameter("bankname").trim());
            s.setContact(request.getParameter("contact").trim());
            s.setPhone(request.getParameter("phone").trim());
            s.setAddr(request.getParameter("addr").trim());
            s.setRemark(request.getParameter("remark").trim());
            base.updateSupplier(s);
            list(request,response);
        }else if(action.equals("del")){
            String supplierId=request.getParameter("supplierId").trim();
            //判断是否能删除
            if(!base.isDels("Product","supplierId",supplierId)){
                base.del("Supplier","supplierId",supplierId);
                return;
            }else {
                out.print(true);
            }
        }
        out.flush();
        out.close();
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

        String page=request.getParameter("page");
        if(page==null){
            page="1";
        }
        if(limit==null){
            limit="3";
        }
        int cnt =base.findCountSupplier();
        //自动生成总页数
        pager.setTotalRows(cnt);
        pager.setPageRow(Integer.parseInt(limit));
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置操作符
        List<SupplierVo> list = base.findPageSupplier(pager.getStartRow(), pager.getPageRow());

        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);
        System.out.println(json);

    }
}
