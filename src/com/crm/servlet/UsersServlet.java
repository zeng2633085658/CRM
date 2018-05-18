package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.AesUtils;
import com.crm.common.ContextUtils;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.DegreesVo;
import com.crm.vo.DepVo;
import com.crm.vo.UsersVo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class UsersServlet extends HttpServlet {
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
        }else if (action.equals("add")){
            UsersVo usersVo=new UsersVo();
            usersVo.setUsername(request.getParameter("title").trim());
            System.out.println(request.getParameter("password"));
            String password=request.getParameter("password").trim();
            usersVo.setPassword(AesUtils.encryptStr(password,AesUtils.SECRETKEY));
            usersVo.setDepid(Integer.parseInt(request.getParameter("depid")));
            usersVo.setDegreeid(Integer.parseInt(request.getParameter("degreeid")));
            usersVo.setJobname(request.getParameter("jobname").trim());
            usersVo.setManagerType(Integer.parseInt(request.getParameter("managerType")));
            usersVo.setMobile(request.getParameter("phone").trim());
            usersVo.setEmail(request.getParameter("email").trim());
            usersVo.setQqcode(request.getParameter("qqcode").trim());
            usersVo.setWeixin(request.getParameter("weixin").trim());
            usersVo.setCardno(request.getParameter("identity").trim());
            usersVo.setBankName(request.getParameter("bankname").trim());
            usersVo.setBankCardNo(request.getParameter("bankcardno").trim());
            usersVo.setJoinDate(request.getParameter("joindate"));
            usersVo.setWorkDate(request.getParameter("workdate"));
            usersVo.setLevelDate(request.getParameter("levelDate"));
            String basesalary=request.getParameter("basesalary");
            if(basesalary.equals("") ){
                basesalary="0";
            }
            usersVo.setBaseSalary(Float.parseFloat(basesalary));
            String degreesalary=request.getParameter("degreesalary");
            if(degreesalary.equals("") ) {
                degreesalary="0";

            }
            usersVo.setDegreeSalary(Float.parseFloat(degreesalary));
            usersVo.setAddr(request.getParameter("addr").trim());
            usersVo.setStatus(1);
            usersVo.setRemark(request.getParameter("remark"));
            base.addUsers(usersVo);
        }else if (action.equals("update")){
            String userid=request.getParameter("userid");
            UsersVo user=base.findUsersId(Integer.parseInt(userid));
            request.setAttribute("user",user);
            List<DepVo> depVos=base.findDep();
            List<DegreesVo> degreesVos=base.findDegrees();
            request.setAttribute("depVos",depVos);
            request.setAttribute("degreesVos",degreesVos);
            request.getRequestDispatcher("/userUpdate.jsp").forward(request,response);
        }else if (action.equals("updateSave")){
            UsersVo usersVo=new UsersVo();
            usersVo.setUserid(Integer.parseInt(request.getParameter("userid")));
            usersVo.setDepid(Integer.parseInt(request.getParameter("depid")));
            usersVo.setDegreeid(Integer.parseInt(request.getParameter("degreeid")));
            usersVo.setJobname(request.getParameter("jobname").trim());
            usersVo.setManagerType(Integer.parseInt(request.getParameter("managerType")));
            usersVo.setEmail(request.getParameter("email").trim());
            usersVo.setQqcode(request.getParameter("qqcode").trim());
            usersVo.setWeixin(request.getParameter("weixin").trim());
            usersVo.setCardno(request.getParameter("identity").trim());
            usersVo.setBankName(request.getParameter("bankname").trim());
            usersVo.setBankCardNo(request.getParameter("bankcardno").trim());
            usersVo.setJoinDate(request.getParameter("joindate"));
            usersVo.setWorkDate(request.getParameter("workdate"));
            usersVo.setLevelDate(request.getParameter("levelDate"));
            String basesalary=request.getParameter("basesalary");
            if(basesalary.equals("") ){
                basesalary="0";
            }
            usersVo.setBaseSalary(Float.parseFloat(basesalary));
            String degreesalary=request.getParameter("degreesalary");
            if(degreesalary.equals("") ) {
                degreesalary="0";
            }
            usersVo.setDegreeSalary(Float.parseFloat(degreesalary));
            usersVo.setAddr(request.getParameter("addr").trim());
            usersVo.setRemark(request.getParameter("remark"));
            base.updateUsers(usersVo);
            list(request,response);
        }else if(action.equals("init")){
            List<DepVo> depVos=base.findDep();
            List<DegreesVo> degreesVos=base.findDegrees();
            request.setAttribute("depVos",depVos);
            request.setAttribute("degreesVos",degreesVos);
            request.getRequestDispatcher("/userAdd.jsp").forward(request,response);
        }else if(action.equals("updateStatus")){
           String userid=request.getParameter("userid");
           int status=Integer.parseInt(request.getParameter("status"));
           String str="";
           if(!base.updateStatus(Integer.parseInt(userid))){
               base.updateStatus(Integer.parseInt(userid),1);
               out.print("启用");
           }else {
               base.updateStatus(Integer.parseInt(userid),0);
               out.print("禁用");
           }
        }else if(action.equals("pwdUpdate")){
            HttpSession session=request.getSession();
            UsersVo user=(UsersVo) session.getAttribute("user");
            String password=request.getParameter("newpass").trim();
            password=AesUtils.encryptStr(password,AesUtils.SECRETKEY);
            base.updatePassword(user.getUserid(),password);
            request.getRequestDispatcher("/exit.jsp").forward(request,response);
        }else if(action.equals("pwdUpdates")){
            System.out.println(request.getParameter("param"));
            String password=request.getParameter("password");
            HttpSession session=request.getSession();
            UsersVo user=(UsersVo) session.getAttribute("user");
            password=AesUtils.encryptStr(password,AesUtils.SECRETKEY);
            System.out.println(password);
            if(password.equals(user.getPassword())){
                out.print("密码一致");
            }else{
                out.print("输入错误");
            }
        }else if(action.equals("isUserName")){
            String username=request.getParameter("username");
            if(base.isUser(username)){
                out.print("用户名已存在");
            }else{
                out.print("用户名可用");
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
        System.out.println(limit);
        String page=request.getParameter("page");
        if(page==null){
            page="1";
        }
        if(limit==null){
            limit="5";
        }
        int cnt =base.findCountUsers();
        //自动生成总页数
        pager.setTotalRows(cnt);
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置一页显示的记录数
        pager.setPageRow(Integer.parseInt(limit));
        //设置操作符
        List<UsersVo> list = base.findPageUsers(pager.getStartRow(), pager.getPageRow());
        //查找部门
        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
