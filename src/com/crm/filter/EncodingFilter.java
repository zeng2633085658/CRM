package com.crm.filter;

import javax.servlet.*;
import java.io.IOException;

//过滤器
public class EncodingFilter implements Filter {
    private FilterConfig filterConfig;
    private String encoding;
    //初始化方法
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
        encoding=filterConfig.getInitParameter("encoding");
    }

    //过滤器的进行过滤处理的主要方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(encoding!=null && !encoding.equals("")){
            //编码处理
            servletRequest.setCharacterEncoding(encoding);
        }
        //该语句非常重要，如果没有执行，程序将不会收的任何请求
        filterChain.doFilter(servletRequest,servletResponse);
    }


    //销毁方法
    @Override
    public void destroy() {

    }
}
