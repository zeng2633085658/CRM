package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.RenamePolicy;
import com.oreilly.servlet.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
/*
	 * 文件上传： 1.要把cos.jar文件拷贝到WEB-INF/lib文件夹 2.创建上传的jsp页面，页面的表单必须有如下2个属性，并且值是固定的
	 * 1.enctype="multipart/form-data" 2.method = "post"
	 * 3.FileRenameUtil改类主要功能是对文件进行重命名，该类必须实现FileRenamePolicy接口
	 * 4.创建文件上传的servlet，实现文件上传
	 *
	 *
	 */
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        JSONObject jsonObject=new JSONObject();
        //获取项目的跟路径:request.getRealPath("/") ||  request.getSession().getServletContext().getRealPath("/")
        String filePath=request.getSession().getServletContext().getRealPath("/")+"uploadFile";
        System.out.println(filePath);
        File uploadFile=new File(filePath);
        //判断文件夹是否存在
        if(!uploadFile.exists()){
            //创建文件
            uploadFile.mkdir();
        }

        //上传文件的最大容量(3M)
        int fileMaxSize=1024*1024*3;
        //存放文件描述
        @SuppressWarnings("unused")
        String[] fileDiscription={null,null};
        //文件名
        String fileName=null;
        //文件个数
        int fileCount=0;
        //重命名策略
        RenamePolicy renamePolicy=new RenamePolicy();
        //上传文件
        MultipartRequest muilt=null;
        try {
             muilt = new MultipartRequest(request, filePath, fileMaxSize, "UTF-8", renamePolicy);
        }catch(Exception e){
            jsonObject.put("error","图片不能超过3MB");
            out.print(jsonObject.toJSONString());
            return;
        }
        //取得上传的所有文件(相当于标识)
        Enumeration filename=muilt.getFileNames();
        while(filename.hasMoreElements()){
            //控件名称
            String name=(String) filename.nextElement();
            System.out.println(name);
            //取得文件名
            fileName=muilt.getFilesystemName(name);
            // 工具标识取得的文件类型
            String contentType=muilt.getContentType(fileName);
            System.out.println("fileName="+fileName);
            System.out.println("contentType = " + contentType);
        }

        jsonObject.put("fileCount",fileCount);
        jsonObject.put("fileName",fileName);
        System.out.println(jsonObject.toJSONString());
        out.print(jsonObject.toJSONString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
