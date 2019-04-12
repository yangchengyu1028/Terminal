package com.ycy.controller;

import com.ycy.entity.UserEntity;
import com.ycy.service.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserEntityService userEntityService;

    /**
     * 存在返回提示信息，不存在添加
     * @param userEntity
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(UserEntity userEntity, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UserEntity userEntity1 = userEntityService.query(userEntity.getMobile());
        if (userEntity1==null){
            userEntityService.addUser(userEntity);
            session.setAttribute("userInfo",userEntity);
            return "1";
        }
        userEntity.setId(userEntity1.getId());
        int num = userEntityService.update(userEntity);
        if (num==0){
            return "2";
        }
        session.setAttribute("userInfo",userEntity);
        return "0";
    }
    @RequestMapping("/getUser")
    public UserEntity getUser(HttpServletRequest request){

        return (UserEntity)request.getSession(false).getAttribute("userInfo");
    }
}
