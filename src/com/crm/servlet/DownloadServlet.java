package com.crm.servlet;

import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.ContractAttachVo;
import com.crm.vo.UsersVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadServlet extends HttpServlet {
    private String contract_id;
    BaseDAO base=new BaseDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        download(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            //项目路径
            String filePath=request.getSession().getServletContext().getRealPath("/")+"uploadFile/";
            //文件默认的名称
            String downloadFilename = "附件.zip";
            //转换中文否则可能会产生乱码
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            // 指明response的返回对象是文件流
            response.setContentType("application/octet-stream");
            // 设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment="+downloadFilename);
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

            //获取附件地址
            List<ContractAttachVo> list=base.findPageContractAttach(0,1000,contract_id);
            String[] files=new String[list.size()];
            for(int i=0;i<list.size();i++){
                ContractAttachVo c=list.get(i);
                files[i]=filePath+c.getAttachURL();
                System.out.println(files[i]);
            }
            System.out.println(files.length);
            for (int i = 0; i < files.length; i++) {
//                URL url = new URL(files[i]);
                zos.putNextEntry(new ZipEntry(i + ".jpg"));
                FileInputStream fis = new FileInputStream(new File(files[i]));
//                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
