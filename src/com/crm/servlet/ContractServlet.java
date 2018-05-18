package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.ContextUtils;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.ContractVo;
import com.crm.vo.CustomerInfoVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
//合同表
public class ContractServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private String custId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session=request.getSession();
        UsersVo u=(UsersVo) session.getAttribute("user");
        if(u==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        //获取客户编号
        custId=request.getParameter("custId");
        if(custId!=null){
            session.setAttribute("custId",custId);
        }else{
            custId =(String) session.getAttribute("custId");
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        if (action.equals("list")) {
            list(request, response);
        }else if(action.equals("add")){
            ContractVo c=new ContractVo();
            c.setContract_no(request.getParameter("title").trim());
            c.setCustId(Integer.parseInt(custId));
            c.setContract_time(request.getParameter("contract_time").trim());
            c.setDue_time(request.getParameter("due_time").trim());
            c.setTotal_money(Float.parseFloat(request.getParameter("total_money")));
            c.setPay_type(request.getParameter("pay_type").trim());
            c.setStatus(request.getParameter("status").trim());
            c.setEmpid(Integer.parseInt(request.getParameter("empid")));
            c.setOprtime(ContextUtils.dateToStrLong(new Date()));
            c.setOperator(u.getUserid());
            c.setContract_name(request.getParameter("contract_name").trim());
            c.setRemark(request.getParameter("remark"));
            base.addContract(c);
            list(request, response);
        }else if(action.equals("update")){
            List<UsersVo> users=base.findUsers();
            List<CustomerInfoVo> customerInfos=base.findCustomerInfos();
            request.setAttribute("usersVos",users);
            request.setAttribute("customerInfos",customerInfos);
            ContractVo contractVo=base.findContractId(Integer.parseInt(request.getParameter("contract_id")));
            request.setAttribute("contract",contractVo);
            request.getRequestDispatcher("/contractUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            ContractVo c=new ContractVo();
            c.setContract_id(Integer.parseInt(request.getParameter("contract_id")));
            c.setContract_no(request.getParameter("title").trim());
            c.setCustId(Integer.parseInt(custId));
            c.setContract_time(request.getParameter("contract_time").trim());
            c.setDue_time(request.getParameter("due_time").trim());
            c.setTotal_money(Float.parseFloat(request.getParameter("total_money")));
            c.setPay_type(request.getParameter("pay_type").trim());
            c.setStatus(request.getParameter("status").trim());
            c.setEmpid(Integer.parseInt(request.getParameter("empid")));
            c.setOprtime(ContextUtils.dateToStrLong(new Date()));
            c.setOperator(u.getUserid());
            c.setContract_name(request.getParameter("contract_name").trim());
            c.setRemark(request.getParameter("remark"));
            base.updateContract(c);
            list(request, response);
        }else if(action.equals("init")){
            List<UsersVo> users=base.findUsers();
            List<CustomerInfoVo> customerInfos=base.findCustomerInfos();
            request.setAttribute("usersVos",users);
            request.setAttribute("customerInfos",customerInfos);
            request.getRequestDispatcher("/contractAdd.jsp").forward(request,response);
        }else if(action.equals("lists")){
            request.getRequestDispatcher("/contractList.jsp").forward(request,response);
        }else if(action.equals("del")){
            String contract_id=request.getParameter("contract_id").trim();
            //判断是否能删除
            if(!base.isDel("Contract","contract_id","ContractAttach",contract_id)){
                base.del("Contract","contract_id",contract_id);
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
            limit="50";
        }
        int cnt =base.findCountContract(custId);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<ContractVo> list = base.findPageContract(pager.getStartRow(), pager.getPageRow(),custId);
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
