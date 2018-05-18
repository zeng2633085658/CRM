package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.DepVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//部门
public class DepServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action");
        if(action==null){
            action="list";
        }
        if(action.equals("add")){
            DepVo depVo=new DepVo();
            depVo.setDepname(request.getParameter("depname").trim());
            depVo.setChairman(request.getParameter("chairman").trim());
            depVo.setDescription(request.getParameter("description").trim());
            if(depVo!=null){
                base.addDep(depVo);
            }
            list(request,response);
        }else if(action.equals("list")){
            list(request,response);
        }else if(action.equals("update")){
            String depid=request.getParameter("depid").trim();
            DepVo depVo=base.findDepId(Integer.parseInt(depid));
            request.setAttribute("dep",depVo);
            request.getRequestDispatcher("/depUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            DepVo depVo=new DepVo();
            depVo.setDepid(Integer.parseInt(request.getParameter("depid")));
            depVo.setDepname(request.getParameter("depname").trim());
            depVo.setChairman(request.getParameter("chairman").trim());
            depVo.setDescription(request.getParameter("description").trim());
            base.updateDep(depVo);
            String goNum=request.getParameter("goNum");
            list(request,response);
        }else if(action.equals("del")){
            String depid=request.getParameter("depid").trim();
            //判断是否能删除
            if(!base.isDels("Users","depid",depid)){
                base.del("Dep","depid",depid);
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
        int cnt =base.findCountDep();
        //自动生成总页数
        pager.setTotalRows(cnt);
        pager.setPageRow(Integer.parseInt(limit));
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置操作符
        List<DepVo> list = base.findPageDep(pager.getStartRow(), pager.getPageRow());

        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String josn=object.toJSONString();
        out.print(josn);

    }
}
