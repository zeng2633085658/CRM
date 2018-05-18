package com.crm.servlet;

import com.alibaba.fastjson.JSONObject;
import com.crm.common.ContextUtils;
import com.crm.common.PageObject;
import com.crm.dao.zyp.BaseDAO;
import com.crm.vo.ProductVo;
import com.crm.vo.SupplierVo;
import com.crm.vo.UnitVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class ProductServlet extends HttpServlet {
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
        }else if(action.equals("add")){
            ProductVo p=new ProductVo();
            Date d=new Date();
            String id= "P"+ContextUtils.dateToStrShort1(d);
            p.setProdid(id);
            p.setProdname(request.getParameter("title").trim());
            p.setProdModel(request.getParameter("prodmodel").trim());
            p.setProdUnit(Integer.parseInt(request.getParameter("produnit")));
            p.setProdStyle(request.getParameter("prodstyle").trim());
            p.setProdCount(Integer.parseInt(request.getParameter("prodcounts")));
            p.setInPrice(Float.parseFloat(request.getParameter("inprice")));
            p.setSalePrice(Float.parseFloat(request.getParameter("saleprice")));
            p.setLowSalePrice(Float.parseFloat(request.getParameter("lowsaleprice")));
            p.setProdSerial(request.getParameter("prodserial").trim());
            p.setCdKey(request.getParameter("cdkey").trim());
            p.setSaleStatus("未售");
            p.setSupplierId(Integer.parseInt(request.getParameter("supplierid")));
            p.setRemark(request.getParameter("remark").trim());
            base.addProduct(p);
            list(request,response);
        }else if(action.equals("update")){
            String prodid=request.getParameter("prodid");
            ProductVo productVo=base.findProductId(prodid);
            request.setAttribute("pro",productVo);
            List<UnitVo> unitVos=base.findUnit();
            List<SupplierVo> supplierVos=base.findSupplier();
            request.setAttribute("unitVos",unitVos);
            request.setAttribute("supplierVos",supplierVos);

            request.getRequestDispatcher("/proUpdate.jsp").forward(request,response);
        }else if(action.equals("updateSave")){
            ProductVo p=new ProductVo();
            p.setProdid(request.getParameter("prodid").trim());
            p.setProdname(request.getParameter("title").trim());
            p.setProdModel(request.getParameter("prodmodel").trim());
            p.setProdUnit(Integer.parseInt(request.getParameter("produnit")));
            p.setProdStyle(request.getParameter("prodstyle").trim());
            p.setProdCount(Integer.parseInt(request.getParameter("prodcounts")));
            p.setInPrice(Float.parseFloat(request.getParameter("inprice")));
            p.setSalePrice(Float.parseFloat(request.getParameter("saleprice")));
            p.setLowSalePrice(Float.parseFloat(request.getParameter("lowsaleprice")));
            p.setProdSerial(request.getParameter("prodserial").trim());
            p.setCdKey(request.getParameter("cdkey").trim());
            p.setSupplierId(Integer.parseInt(request.getParameter("supplierid")));
            p.setRemark(request.getParameter("remark").trim());
            base.updateProduct(p);
            list(request,response);
        }else if(action.equals("init")){
            List<UnitVo> unitVos=base.findUnit();
            List<SupplierVo> supplierVos=base.findSupplier();
            request.setAttribute("unitVos",unitVos);
            request.setAttribute("supplierVos",supplierVos);
            request.getRequestDispatcher("/proAdd.jsp").forward(request,response);
        }else if(action.equals("uodateSaleStatus")){
            String prodid=request.getParameter("prodid").trim();
            System.out.println(prodid);
            String saleStatus=request.getParameter("saleStatus").trim();
            if(!base.updateSaleStatus(prodid)){
                base.updateSaleStatus(prodid,"未售");
                out.print("未售");
            }else{
                base.updateSaleStatus(prodid,"已售");
                out.print("已售");

            }
        }else if(action.equals("del")){
            String prodid=request.getParameter("prodid").trim();
            //判断是否能删除
            if(!base.isDels("OrderDetail","prodid",prodid)){
                base.del("Product","prodid",prodid);
                return;
            }else {
                out.print(true);
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
        String page=request.getParameter("page");
        if(page==null){
            page="1";
        }
        if(limit==null){
            limit="10";
        }
        int cnt =base.findCountProduct();
        //自动生成总页数
        pager.setTotalRows(cnt);
        pager.setPageRow(Integer.parseInt(limit));
        //设置当前页数
        pager.setCur_page(Integer.parseInt(page));
        //设置操作符
        List<ProductVo> list = base.findPageProduct(pager.getStartRow(), pager.getPageRow());

        JSONObject object=new JSONObject();
        object.put("code",0);
        object.put("count",cnt);
        object.put("data",list);
        String json=object.toJSONString();
        out.print(json);

    }
}
