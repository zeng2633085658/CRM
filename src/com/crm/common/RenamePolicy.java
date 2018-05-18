package com.crm.common;

import com.oreilly.servlet.multipart.FileRenamePolicy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//对上传文件进行重命名
public class RenamePolicy implements FileRenamePolicy{
    @Override
    public File rename(File file) {
        String body="";
        String exit="";
        //获取系统当前时间
        Date date=new Date();
        //MM代表月,mm代表分,HH代表24小时制,hh代表12小时制
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        //取得文件名和后缀分割点
        int post=file.getName().lastIndexOf(".");
        if(post!=-1){
            //文件名
            body=sdf.format(date);
            //拓展名,截取后缀名
            exit=file.getName().substring(post);
        }else{
            body=(new Date()).getTime()+"";
            exit="";
        }

        //随机数
        Random ran=new Random();
        int rnd=Math.abs(ran.nextInt(9000)+1000);
        //新的文件名称
        String fileName=body+rnd+exit;
        //对文件进行重命名
        file=new File(file.getParent(),fileName);
        return file;
    }
}
