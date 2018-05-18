package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.DegreesVo;
import com.crm.vo.DepVo;
import com.crm.vo.UnitVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//岗位
public class DegreesServlet extends HttpServlet {
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
            DegreesVo degreesVo=new DegreesVo();
            degreesVo.setDegreename(request.getParameter("degreename").trim());
            base.addDegrees(degreesVo);
            list(request,response);
        }else if(action.equals("update")){
            String degreeid=request.getParameter("degreeid").trim();
            DegreesVo degreesVo=base.findDegreesId(Integer.parseInt(degreeid));
            request.setAttribute("degreesVo",degreesVo);
            request.getRequestDispatcher("/degUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            DegreesVo degreesVo=new DegreesVo();
            degreesVo.setDegreeid(Integer.parseInt(request.getParameter("degreeid")));
            degreesVo.setDegreename(request.getParameter("degreename").trim());
            base.updateDegrees(degreesVo);
            list(request,response);
        }else if(action.equals("del")){
            String degreeid=request.getParameter("degreeid").trim();
            //判断是否能删除
            if(!base.isDels("Users","degreeid",degreeid)){
                base.del("Degrees","degreeid",degreeid);
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
        int cnt =base.findCountDegrees();
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<DegreesVo> list = base.findPageDegrees(pager.getStartRow(), pager.getPageRow());


        request.setAttribute("list", list);
        request.setAttribute("pager", pager);
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String josn=object.toJSONString();
        out.print(josn);

    }
}
