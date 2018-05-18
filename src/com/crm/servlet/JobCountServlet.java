package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.CustomerInfoVo;
import com.crm.vo.JobRecordVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



public class JobCountServlet extends HttpServlet {
    BaseDAO base = new BaseDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        if (action.equals("list")) {
            list(request, response,null,null,null,null);
        } else if (action.equals("init")) {
            List<CustomerInfoVo> customerInfoVos = base.findCustomerInfos();
            List<UsersVo> usersVos = base.findUsers();
            request.setAttribute("customerInfoVos", customerInfoVos);
            request.setAttribute("usersVos", usersVos);
            request.getRequestDispatcher("/jobListCount.jsp").forward(request, response);

        } else if (action.equals("select")) {
            String customerInfoVos=null;
            String usersVos=null;
            String stime=null;
            String etime=null;
            customerInfoVos = request.getParameter("customerInfoVos");
            System.out.println(customerInfoVos);
            usersVos = request.getParameter("usersVos");
            String workdate = request.getParameter("workdate");
            System.out.println("workdate="+workdate);
            if(workdate!=null){
                if(!workdate.equals("")){
                    int index=workdate.indexOf('~');
                    stime=workdate.substring(0,index).trim();
                    etime=workdate.substring(index+1,workdate.length()).trim();
                }
            }

            list(request, response,customerInfoVos,usersVos,stime,etime);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //分页查询
    private void list(HttpServletRequest request, HttpServletResponse response,String customerInfoVos,String usersVos,String stime,String etime)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        PageObject pager = new PageObject();
        String limit = request.getParameter("limit");
        System.out.println(limit);
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        if (limit == null) {
            limit = "10";
        }
        int cnt = base.findCount("JobRecord");
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<JobRecordVo> list = base.findPageJobRecords(pager.getStartRow(), pager.getPageRow(),customerInfoVos,usersVos,stime,etime);
        //查找部门
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("count", cnt);
        object.put("data", list);
        String json = object.toJSONString();
        out.print(json);

    }
}
