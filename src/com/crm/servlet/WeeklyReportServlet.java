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


public class WeeklyReportServlet extends HttpServlet {
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
            WeeklyReportVo w=new WeeklyReportVo();
            w.setWeekDate(request.getParameter("weekDate"));
            w.setWorkContent(request.getParameter("workContent").trim());
            w.setWorkReview(request.getParameter("workReview").trim());
            w.setQuestion(request.getParameter("question").trim());
            w.setWarning(request.getParameter("warning").trim());
            w.setWeekPlan(request.getParameter("weekPlan").trim());
            w.setUserid(user.getUserid());
            w.setOprtime(ContextUtils.dateToStrLong(new Date()));
            w.setRemark(request.getParameter("remark").trim());
            base.addWeeklyReport(w);
            list(request,response);
        }else if(action.equals("update")){
            WeeklyReportVo w=base.findWeeklyReportId(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("week",w);
            request.getRequestDispatcher("/weekUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            WeeklyReportVo w=new WeeklyReportVo();
            w.setId(Integer.parseInt(request.getParameter("id")));
            w.setWeekDate(request.getParameter("weekDate"));
            w.setWorkContent(request.getParameter("workContent").trim());
            w.setWorkReview(request.getParameter("workReview").trim());
            w.setQuestion(request.getParameter("question").trim());
            w.setWarning(request.getParameter("warning").trim());
            w.setWeekPlan(request.getParameter("weekPlan").trim());
            w.setUserid(user.getUserid());
            w.setOprtime(ContextUtils.dateToStrLong(new Date()));
            w.setRemark(request.getParameter("remark").trim());
            base.updateWeeklyReport(w);
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
            limit="5";
        }
        int cnt =base.findCount("WeeklyReport");
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<WeeklyReportVo> list = base.findPageWeeklyReport(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
