package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.DepVo;
import com.crm.vo.UnitVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UnitServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action");
        if (action==null){
            action="list";
        }
        if(action.equals("list")){
            list(request,response);
        }else if(action.equals("add")){
            UnitVo unitVo=new UnitVo();
            unitVo.setUnitName(request.getParameter("unitName").trim());
            base.addUnit(unitVo);
            list(request,response);
        }else if(action.equals("update")){
            String unitId=request.getParameter("unitId").trim();
            UnitVo unitVo=base.findUnitId(Integer.parseInt(unitId));
            request.setAttribute("unitVo",unitVo);
            request.getRequestDispatcher("/unitUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            UnitVo unitVo=new UnitVo();
            unitVo.setUnitId(Integer.parseInt(request.getParameter("unitId")));
            unitVo.setUnitName(request.getParameter("unitName").trim());
            base.updateUnit(unitVo);
            list(request,response);
        }else if(action.equals("del")){
            String unitId=request.getParameter("unitId").trim();
            //判断是否能删除
            if(!base.isDels("Product","prodUnit",unitId)){
                base.del("Unit","unitId",unitId);
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
        int cnt =base.findCountUnit();
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<UnitVo> list = base.findPageUnit(pager.getStartRow(), pager.getPageRow());

        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String josn=object.toJSONString();
        out.print(josn);


    }
}
