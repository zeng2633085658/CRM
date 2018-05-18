package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.BusinessVo;
import com.crm.vo.ContactVo;
import com.crm.vo.CustomerInfoVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//客户联系人
public class ContactServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String custId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        UsersVo user=(UsersVo) session.getAttribute("user");
        if(user==null){
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
        }else if(action.equals("add")){
            ContactVo c=new ContactVo();
            c.setTalkDate(request.getParameter("talkDate"));
            c.setCustContact(request.getParameter("custContact").trim());
            c.setPhone1(request.getParameter("phone1").trim());
            c.setPhone2(request.getParameter("phone2").trim());
            c.setCustid(Integer.parseInt(custId));
            c.setQqCode(request.getParameter("qqCode").trim());
            c.setEmail(request.getParameter("email").trim());
            c.setWeixin(request.getParameter("weixin").trim());
            c.setUserid(Integer.parseInt(request.getParameter("userid")));
            c.setBirthday(request.getParameter("birthday"));
            c.setHobbit(request.getParameter("hobbit").trim());
            c.setJobName(request.getParameter("jobName").trim());
            c.setRemark(request.getParameter("remark").trim());
            base.addContact(c);
            list(request,response);
        }else if(action.equals("update")){
            List<CustomerInfoVo> c=base.findCustomerInfos();
            List<UsersVo> u=base.findUsers();
            request.setAttribute("cust",c);
            request.setAttribute("users",u);

            ContactVo con=base.findContactId(Integer.parseInt(request.getParameter("contactId")));
            request.setAttribute("con",con);
            request.getRequestDispatcher("/conUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            ContactVo c=new ContactVo();
            c.setContactId(Integer.parseInt(request.getParameter("contactId")));
            c.setTalkDate(request.getParameter("talkDate"));
            c.setCustContact(request.getParameter("custContact").trim());
            c.setPhone1(request.getParameter("phone1").trim());
            c.setPhone2(request.getParameter("phone2").trim());
            c.setCustid(Integer.parseInt(custId));
            c.setQqCode(request.getParameter("qqCode").trim());
            c.setEmail(request.getParameter("email").trim());
            c.setWeixin(request.getParameter("weixin").trim());
            c.setUserid(Integer.parseInt(request.getParameter("userid")));
            c.setBirthday(request.getParameter("birthday"));
            c.setHobbit(request.getParameter("hobbit").trim());
            c.setJobName(request.getParameter("jobName").trim());
            c.setRemark(request.getParameter("remark").trim());
            base.updateContact(c);
            list(request,response);
        }else if(action.equals("init")){
            List<CustomerInfoVo> c=base.findCustomerInfos();
            List<UsersVo> u=base.findUsers();
            request.setAttribute("cust",c);
            request.setAttribute("users",u);
            request.getRequestDispatcher("/conAdd.jsp").forward(request,response);
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
        int cnt =base.findCountContact(custId);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<ContactVo> list = base.findPageContact(pager.getStartRow(), pager.getPageRow(),custId);
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
