package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.ContactVo;
import com.crm.vo.PaidTypeVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class PaidTypeServlet extends HttpServlet {
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
            PaidTypeVo p=new PaidTypeVo();
            p.setPaidtypename(request.getParameter("paidtypename").trim());
            base.addPaidType(p);
            list(request,response);
        }else if(action.equals("update")){
            PaidTypeVo p=base.findPaidTypeId(Integer.parseInt(request.getParameter("paidtypeid")));
            request.setAttribute("paid",p);
            request.getRequestDispatcher("/paidUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            PaidTypeVo p=new PaidTypeVo();
            p.setPaidtypeid(Integer.parseInt(request.getParameter("paidtypeid")));
            p.setPaidtypename(request.getParameter("paidtypename").trim());
            base.updatePaidType(p);
            list(request,response);
        }else if(action.equals("del")){
            String paidtypeid=request.getParameter("paidtypeid").trim();
            //判断是否能删除
            if(!base.isDels("Finance","paidtypeid",paidtypeid)){
                base.del("PaidType","paidtypeid",paidtypeid);
                return;
            }else {
                out.print(true);
            }
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
        int cnt =base.findCount("PaidType");
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<PaidTypeVo> list = base.findPagePaidType(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
