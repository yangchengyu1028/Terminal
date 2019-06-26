package com.ycy.controller;

import com.ycy.entity.OrderGoodsEntity;
import com.ycy.service.ISupplierEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/index")
public class LoginController {
    @Autowired
    private ISupplierEntityService supplierEntityService;

    private static final Logger LOGGER= LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/home")
    public String home(HttpServletRequest request,String mobile){
        int supplierId = 0;
        try {
            supplierId = supplierEntityService.getSupplierByMobile(mobile);
        }catch (Exception e){
            LOGGER.error("通过手机号"+mobile+"查询店铺发现异常"+e);
        }

        if (supplierId==0){
            LOGGER.error("系统中没有手机号的店铺，mobile="+mobile);
            return "Error";
        }
        HttpSession session = request.getSession();
        session.setAttribute("supplierId", supplierId);
        List<OrderGoodsEntity> cartList = new ArrayList<>();
        if (session.getAttribute("LocalCartList")==null){
            session.setAttribute("LocalCartList",cartList);
        }
        List<OrderGoodsEntity> cartList1 = new ArrayList<>();
        if (session.getAttribute("OnlineCartList")==null){
            session.setAttribute("OnlineCartList",cartList1);
        }
        return "home";
    }
    @RequestMapping("/homes")
    public String homes(){
        return "home";
    }

    @RequestMapping("/backIndex")
    public String homes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("userInfo");
        session.removeAttribute("OnlineCartList");
        session.removeAttribute("LocalCartList");
        return "home";
    }



}
