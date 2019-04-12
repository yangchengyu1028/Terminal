package com.ycy.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ycy.entity.SymptomEntity;
import com.ycy.service.ISymptomEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/symptom")
public class SymptomController {

    @Autowired
    private ISymptomEntityService symptomEntityService;

    /**
     * 模糊查询本地症状类型并分页(也可查询所有)
     * @param pageNo
     * @param pageSize
     * @param name 模糊症状名
     * @return
     */
    @RequestMapping("/getLocalSymptomByLike")
    @ResponseBody
    public Page<SymptomEntity> getLocalSymptomByLike(int pageNo, int pageSize, String name, HttpServletRequest request){
        Page<SymptomEntity> page = new Page<>(pageNo,pageSize);

        return symptomEntityService.getSymptomByLike(page,name,(int)request.getSession(false).getAttribute("supplierId"));
    }

    /**
     * 模糊查询网上（拜欧）症状类型并分页(也可查询所有)
     * @param pageNo
     * @param pageSize
     * @param name 模糊症状名
     * @return
     */
    @RequestMapping("/getOnlineSymptomByLike")
    @ResponseBody
    public Page<SymptomEntity> getOnlineSymptomByLike(int pageNo, int pageSize, String name){
        Page<SymptomEntity> page = new Page<>(pageNo,pageSize);

        return symptomEntityService.getSymptomByLike(page,name,61);
    }
}
