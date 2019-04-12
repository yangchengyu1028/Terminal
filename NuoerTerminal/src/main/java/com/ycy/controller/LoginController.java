package com.ycy.controller;

import com.ycy.service.ISupplierEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/index")
public class LoginController {
    @Autowired
    private ISupplierEntityService supplierEntityService;


    @RequestMapping("/home")
    public String home(HttpServletRequest request,String mobile){
        int supplierId = supplierEntityService.getSupplierByMobile(mobile);
        if (supplierId==0){
            return "";
        }
        HttpSession session = request.getSession();
        session.setAttribute("supplierId", supplierId);
        return "home";
    }
    @RequestMapping("/homes")
    public String homes(){
        return "home";
    }



}
