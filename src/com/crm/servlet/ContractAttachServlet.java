package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.ContractAttachVo;
import com.crm.vo.ContractVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//合同附件
public class ContractAttachServlet extends HttpServlet {
    BaseDAO base=new BaseDAO();
    private  String contract_id;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        UsersVo u=(UsersVo) session.getAttribute("user");
        if(u==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        //获取合同编号
        contract_id=request.getParameter("contract_id");
        if(contract_id!=null){
            session.setAttribute("contract_id",contract_id);
        }else{
            contract_id =(String) session.getAttribute("contract_id");
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        if (action.equals("list")) {
            list(request, response);
        }else if(action.equals("add")){
            ContractAttachVo c=new ContractAttachVo();
            c.setContract_id(Integer.parseInt(contract_id));
            c.setSeq(Integer.parseInt(request.getParameter("seq")));
            c.setAttachFile(request.getParameter("attachFile").trim());
            c.setUser_name(u.getUserid());
            c.setAttachURL(request.getParameter("attachURL"));
            base.addContractAttach(c);
            list(request, response);
        }else if(action.equals("update")){
            List<ContractVo> contractVo=base.findContract();
            request.setAttribute("contract",contractVo);
            ContractAttachVo c=base.findContractAttachId(Integer.parseInt(request.getParameter("contractAttach_id")));
            request.setAttribute("cona",c);
            request.getRequestDispatcher("/conaUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            ContractAttachVo c=new ContractAttachVo();
            c.setContractAttach_id(Integer.parseInt(request.getParameter("contractAttach_id")));
            c.setContract_id(Integer.parseInt(contract_id));
            c.setSeq(Integer.parseInt(request.getParameter("seq")));
            c.setAttachFile(request.getParameter("attachFile").trim());
            c.setUser_name(u.getUserid());
            c.setAttachURL(request.getParameter("attachURL"));
            base.updateContractAttach(c);
            list(request, response);
        }else if(action.equals("init")){
            List<ContractVo> contractVo=base.findContract();
            request.setAttribute("contract",contractVo);
            request.getRequestDispatcher("/conaAdd.jsp").forward(request,response);
        }else if(action.equals("findImage")){ //预览合同附件
            //查找合同附件
            System.out.println(contract_id);
            List<ContractAttachVo> list = base.findPageContractAttach(0,1000,contract_id);
            JSONObject object=new JSONObject();
            object.put("img",list);
            String json=object.toJSONString();
            out.print(json);
        }else if(action.equals("del")){
            String contractAttach_id=request.getParameter("contractAttach_id");
            base.del("ContractAttach","contractAttach_id",contractAttach_id);
            list(request, response);
        }else if(action.equals("findImgs")){
            request.getRequestDispatcher("/conaImages.jsp").forward(request,response);
        }else if(action.equals("lists")){
            request.getRequestDispatcher("/contractList.jsp").forward(request,response);
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
        int cnt =base.findCountContractAttach(contract_id);
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<ContractAttachVo> list = base.findPageContractAttach(pager.getStartRow(), pager.getPageRow(),contract_id);

        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
